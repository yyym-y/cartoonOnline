package org.yyym.back.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.yyym.back.mapper.CartoonMapper;
import org.yyym.back.serve.AdminService;
import org.yyym.back.util.entity.Episode_change;
import org.yyym.back.util.helper.Result;

import java.util.List;
import java.util.Map;

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

    @PostMapping("/uploadVideo")
    public Result uploadVideo(@RequestParam("file") MultipartFile multipartfile) {
        return adminService.uploadVideo(multipartfile);
    }

    @RequestMapping(value = "/changeEpisode", method = RequestMethod.POST)
    public Result changeEpisode(@RequestBody List<Episode_change> episodes) {
        return adminService.changeEpisode(episodes);
    }
}
