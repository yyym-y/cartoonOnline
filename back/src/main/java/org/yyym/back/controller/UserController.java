package org.yyym.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yyym.back.serve.UserService;
import org.yyym.back.util.entity.RegisterInfo;
import org.yyym.back.util.helper.Result;

@RestController
@CrossOrigin(originPatterns = "*",allowCredentials = "true")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/logIn")
    public Result logIn(String uid, String password) {
        return userService.logIn(uid, password);
    }
    @RequestMapping("/confirmCode")
    public Result confirmCode(String email) {
        return userService.confirmCode(email);
    }
    @RequestMapping("/register")
    public Result register(RegisterInfo registerInfo) {
        return userService.register(registerInfo);
    }
}
