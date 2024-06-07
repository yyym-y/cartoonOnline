package org.yyym.back.serve;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.yyym.back.mapper.M3u8Mapper;
import org.yyym.back.mapper.multi.AdminTableInfoMapper;
import org.yyym.back.util.entity.AdminTableInfo;
import org.yyym.back.util.entity.Episode_change;
import org.yyym.back.util.entity.M3u8Info;
import org.yyym.back.util.helper.Random;
import org.yyym.back.util.helper.Result;
import org.yyym.back.util.helper.VideoProcess;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminTableInfoMapper adminTableInfoMapper;
    @Autowired
    private M3u8Mapper m3u8Mapper;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    private final String SavePath = "E:\\sources\\cartoon_online\\tem";

    public Result getAllTableInfos() {
        List<AdminTableInfo> res = adminTableInfoMapper.selectAllInfos();
        for(int i = 0 ; i < res.size() ; i++) {
            for(int j = 0 ; j < res.get(i).getEpisodeInfos().size() ; j ++) {
                res.get(i).getEpisodeInfos().get(j).put("type", "ori");
            }
        }
        return Result.success(res);
    }

    public Result uploadVideo(MultipartFile multipartfile) {
        if(multipartfile == null)
            return Result.error("multipartfile is null");
        try {
            String oriName = multipartfile.getOriginalFilename();
            assert oriName != null;
            String newName = Random.generateName() + oriName.substring(oriName.lastIndexOf("."));
            File fileSave = new File(SavePath, newName);
            multipartfile.transferTo(fileSave);
            return Result.success(newName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Result.success();
    }

    public void process(Episode_change episode) {
        String Name = Random.generateName();
        m3u8Mapper.insert(new M3u8Info(episode.getCartoonId(), episode.getNum(), Name + ".m3u8"));
        VideoProcess.process(episode.getCartoonId(), episode.getNum(), episode.getM3u8_url(), Name);
    }

    public Result changeEpisode(List<Episode_change> episodes) {
        for (Episode_change pr : episodes) {
            process(pr);
        }
        return Result.success("final");
    }
}
