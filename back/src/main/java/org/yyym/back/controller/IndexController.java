package org.yyym.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yyym.back.serve.IndexService;
import org.yyym.back.util.entity.Tag;
import org.yyym.back.util.helper.Result;

import java.util.List;

@RestController
@CrossOrigin(originPatterns = "*",allowCredentials = "true")
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private IndexService indexService;

    @RequestMapping("/getMenu")
    public Result getMenu() {
        return indexService.getMenu();
    }
    @RequestMapping("/getCarousel")
    public Result getCarousel() {
        return indexService.getCarousel();
    }

    @RequestMapping(value = "/getTagMenu", method = RequestMethod.POST)
    public Result getTagMenu(@RequestBody List<Tag> choices) {
        return indexService.getTagMenu(choices);
    }
}
