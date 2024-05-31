package org.yyym.back.serve;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.easysdk.factory.Factory;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yyym.back.config.AliPayConfig;
import org.yyym.back.mapper.OrderMapper;
import org.yyym.back.mapper.UserInfoMapper;
import org.yyym.back.util.entity.Order;
import org.yyym.back.util.entity.UserInfo;
import org.yyym.back.util.helper.Random;
import org.yyym.back.util.helper.Result;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class PayService {
    @Resource
    AliPayConfig aliPayConfig;

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private OrderMapper orderMapper;

    private static final String GATEWAY_URL ="https://openapi-sandbox.dl.alipaydev.com/gateway.do";
    private static final String FORMAT ="JSON";
    private static final String CHARSET ="utf-8";
    private static final String SIGN_TYPE ="RSA2";

    public void createOrder(String orderId ,String uid) throws Exception{
        int res = orderMapper.insert(new Order(orderId, uid));
        if(res == 0)
            throw new Exception("insert error");
    }

    public String queryAndUpdate(String orderId, Map<String, String> params) throws Exception {
        System.out.println(orderId);
        String uid = orderMapper.selectOne(new QueryWrapper<Order>().eq("order_id", orderId)).getUid();
        if(uid == null)
            throw new Exception("orderId null");
        int res = orderMapper.update(new UpdateWrapper<Order>()
                .set("buyer_id", params.get("buyer_id"))
                .set("buyer_pay_amount", params.get("buyer_pay_amount"))
                .set("trade_no", params.get("trade_no"))
                .set("gmt_payment", new Date())
                .eq("order_id", orderId));
        if (res == 0)
            throw new Exception("update error");
        return uid;
    }

    public void pay(String userId, HttpServletResponse httpResponse) {
        AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, aliPayConfig.getAppId(),
                aliPayConfig.getAppPrivateKey(), FORMAT, CHARSET, aliPayConfig.getAlipayPublicKey(), SIGN_TYPE);
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setNotifyUrl(aliPayConfig.getNotifyUrl());
        request.setReturnUrl(aliPayConfig.getReturnUrl());
        try {
            String orderId = Random.generateName();
            request.setBizContent("{\"out_trade_no\":\""+ orderId +"\", \"product_code\": \"FAST_INSTANT_TRADE_PAY\", \"total_amount\": 1, \"subject\": \"VIP开通\", \"timeout_express\": \"5m\"}");
            createOrder(orderId, userId);
            String form = "";
            form = alipayClient.pageExecute(request).getBody();
            httpResponse.setContentType("text/html;charset=" + CHARSET);
            httpResponse.getWriter().write(form);
            httpResponse.getWriter().flush();
            httpResponse.getWriter().close();
        } catch (AlipayApiException | IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Result payNotify(HttpServletRequest request) {
        if (request.getParameter("trade_status").equals("TRADE_SUCCESS")) {
            Map<String, String> params = new HashMap<>();
            Map<String, String[]> requestParams = request.getParameterMap();
            for (String name : requestParams.keySet()) {
                params.put(name, request.getParameter(name));
            }
            // 支付宝验签
            try {
                if (Factory.Payment.Common().verifyNotify(params)) {
                    String uid = queryAndUpdate(params.get("out_trade_no"), params);
                    userInfoMapper.update(new UpdateWrapper<UserInfo>()
                            .eq("uid", uid).set("type", 2));
                }
            } catch(Exception e) {
                e.printStackTrace();
                return Result.error("验签失败");
            }

        }
        return Result.success();
    }
}
