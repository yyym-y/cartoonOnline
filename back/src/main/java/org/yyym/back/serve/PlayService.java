package org.yyym.back.serve;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yyym.back.mapper.EpisodeMapper;
import org.yyym.back.util.Episode;
import org.yyym.back.util.Result;

@Service
public class PlayService {
    @Autowired
    private EpisodeMapper episodeMapper;

    public Result getEpisode(String cartoonId) {
        Episode result = episodeMapper.selectById(cartoonId);
        if(result == null)
            return Result.error("Cartoon ID not find");
        return Result.success(result);
    }
}
