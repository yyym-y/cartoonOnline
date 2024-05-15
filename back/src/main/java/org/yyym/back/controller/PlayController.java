package org.yyym.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yyym.back.serve.PlayService;
import org.yyym.back.util.helper.Result;

@RestController
@CrossOrigin(originPatterns = "*",allowCredentials = "true")
@RequestMapping("/play")
public class PlayController {
    @Autowired
    private PlayService playService;

    @RequestMapping("/getVideo")
    public Result getVideo(String cartoonId) {
        return playService.getEpisode(cartoonId);
    }
}
