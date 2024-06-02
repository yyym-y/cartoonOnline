package org.yyym.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yyym.back.mapper.CartoonMapper;
import org.yyym.back.serve.AdminService;
import org.yyym.back.util.helper.Result;

@RestController
@CrossOrigin(originPatterns = "*",allowCredentials = "true")
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/getAllTableInfos")
    public Result getAllTableInfos() {
        return adminService.getAllTableInfos();
    }
}
