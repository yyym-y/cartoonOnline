package org.yyym.back.serve;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yyym.back.mapper.UserInfoMapper;
import org.yyym.back.util.Jwt;
import org.yyym.back.util.Result;
import org.yyym.back.util.UserInfo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    public Result logIn(String uid, String password) {
        UserInfo res;
        if ( uid.contains("@") ) {
            res = userInfoMapper.selectOne(new QueryWrapper<UserInfo>()
                    .eq("email", uid)
                    .eq("password", password));
        } else {
            res = userInfoMapper.selectOne(new QueryWrapper<UserInfo>()
                    .eq("username", uid)
                    .eq("password", password));
        }
        if (res == null )
            return Result.error("uid or passwd wrong");
        Map<String, Object> returnInfo = new HashMap<>();
        returnInfo.put("uid", res.getUid());
        returnInfo.put("jwt", Jwt.getJwt(
                returnInfo, 30, "yyym"
        ));
        return Result.success(returnInfo);
    }
}
