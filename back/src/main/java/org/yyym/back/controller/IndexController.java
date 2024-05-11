package org.yyym.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yyym.back.serve.IndexService;
import org.yyym.back.util.Result;

@RestController
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private IndexService indexService;

    @RequestMapping("/getMenu")
    public Result getMenu() {
        return indexService.getMenu();
    }
}