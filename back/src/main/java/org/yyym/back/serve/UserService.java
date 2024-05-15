package org.yyym.back.serve;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.yyym.back.mapper.ConfirmCodeMapper;
import org.yyym.back.mapper.UserInfoMapper;
import org.yyym.back.util.entity.ConfirmCode;
import org.yyym.back.util.entity.RefindInfo;
import org.yyym.back.util.entity.RegisterInfo;
import org.yyym.back.util.helper.Jwt;
import org.yyym.back.util.helper.Random;
import org.yyym.back.util.helper.Result;
import org.yyym.back.util.entity.UserInfo;
import org.yyym.back.util.helper.SendEmail;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private ConfirmCodeMapper confirmCodeMapper;
    @Autowired
    private SendEmail sender;

    public Result logIn(String uid, String password) {
        UserInfo res;
        if ( uid.contains("@") ) {
            res = userInfoMapper.selectOne(new QueryWrapper<UserInfo>()
                    .select(List.of("uid", "username", "email", "type"))
                    .eq("email", uid)
                    .eq("password", password));
        } else {
            res = userInfoMapper.selectOne(new QueryWrapper<UserInfo>()
                    .select(List.of("uid", "username", "email", "type"))
                    .eq("username", uid)
                    .eq("password", password));
        }
        if (res == null )
            return Result.error("uid or passwd wrong");
        Map<String, Object> returnInfo = new HashMap<>();
        returnInfo.put("user", res);
        returnInfo.put("jwt", Jwt.getJwt(
                returnInfo, 300, "yyym"
        ));
        return Result.success(returnInfo);
    }

    public Result confirmCode(String email) {
        String code = Random.generateName().substring(0, 7);
        clearCode(email);
        int res = confirmCodeMapper.insert(new ConfirmCode(email, code, new Date()));
        if(res == 0)
            return Result.error("code insert error");
        sender.send(email, "验证码", code);
        return Result.success();
    }

    public Result register(RegisterInfo registerInfo) {
        if(!registerInfo.getPassword().equals(registerInfo.getRe_password()))
            return Result.error("re_passwd != passwd");
        Result result = checkCode(registerInfo.getEmail(), registerInfo.getConfirmCode());
        if(result.getCode() == 0)
            return result;
        Map<String, Object> infos = new HashMap<>();
        UserInfo user = new UserInfo(Random.generateName().substring(0, 7),
                registerInfo.getUsername(), registerInfo.getPassword(),
                registerInfo.getEmail(), 1);
        infos.put("uid", user.getUid());
        try{
            int res = userInfoMapper.insert(user);
        } catch (DuplicateKeyException e) {
            return Result.error("email has been used");
        }
        infos.put("jwt", Jwt.getJwt(infos, 300, "yyym"));
        return Result.success(infos);
    }

    public Result refind(RefindInfo refindInfo) {
        if(!refindInfo.getPassword().equals(refindInfo.getRe_password()))
            return Result.error("re_passwd != passwd");
        Result result = checkCode(refindInfo.getEmail(), refindInfo.getConfirmCode());
        if(result.getCode() == 0)
            return result;
        int res = userInfoMapper.update(new UpdateWrapper<UserInfo>()
                .eq("email", refindInfo.getEmail())
                .set("password", refindInfo.getPassword()));
        if(res == 0)
            return Result.error("email not find");
        return Result.success();
    }

    public Result checkJwt(String jwt) {
        return Jwt.decodeJwt(jwt, "yyym");
    }

    private void clearCode() {
        confirmCodeMapper.delete(new QueryWrapper<ConfirmCode>()
                .lt("time", new Date(new Date().getTime() - 200000)));
    }
    private void clearCode(String email) {
        confirmCodeMapper.delete(new QueryWrapper<ConfirmCode>()
                .eq("email", email));
        clearCode();
    }
    private Result checkCode(String email, String code) {
        ConfirmCode confirmCode = confirmCodeMapper.selectOne(new QueryWrapper<ConfirmCode>()
                .eq("email", email).eq("code", code));
        if (confirmCode == null) return Result.error("confirm code wrong");
        long diff = Math.abs(new Date().getTime() - confirmCode.getTime().getTime());
        if(diff <= 200000)
            return Result.success();
        return Result.error("confirm code Time Out");
    }
}
