package org.yyym.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yyym.back.serve.DataService;
import org.yyym.back.util.helper.Result;

@RestController
@CrossOrigin(originPatterns = "*",allowCredentials = "true")
@RequestMapping("/data")
public class DataController {
    @Autowired
    private DataService dataService;

    @RequestMapping("/getPlayLineData")
    public Result getPlayLineData() {
        return dataService.getPlayLineData();
    }

    @RequestMapping("/getAllPlayData")
    public Result getAllPlayData() {
        return dataService.getAllPlayData();
    }

    @RequestMapping("/getYearPlayData")
    public Result getYearPlayData(String year) {
        return dataService.getYearPlayData(year);
    }

    @RequestMapping("/getMonthPlayData")
    public Result getMonthPlayData() {
        return dataService.getMonthPlayData();
    }
}
