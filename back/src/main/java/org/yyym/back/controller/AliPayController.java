package org.yyym.back.controller;


import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.easysdk.factory.Factory;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yyym.back.config.AliPayConfig;
import org.yyym.back.serve.PayService;
import org.yyym.back.util.helper.AliPay;
import org.yyym.back.util.helper.Random;
import org.yyym.back.util.helper.Result;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("alipay")
@Transactional(rollbackFor = Exception.class)
public class AliPayController {

    @Autowired
    private PayService payService;

    @GetMapping("/pay")
    public void pay(String userId, HttpServletResponse httpResponse) {
        payService.pay(userId, httpResponse);
    }

    @PostMapping("/notify")
    public Result payNotify(HttpServletRequest request) {
        return payService.payNotify(request);
    }

}