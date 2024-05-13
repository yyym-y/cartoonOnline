package org.yyym.back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.yyym.back.util.Episode;

@Mapper
public interface EpisodeMapper extends BaseMapper<Episode> {
    @Results({
            @Result(column = "cartoon_id", property = "cartoonId"),
            @Result(column = "cartoon_id", property = "infos",
                    many = @Many(select = "org.yyym.back.mapper.M3u8Mapper.selectById"))
    })
    @Select("SELECT * FROM cartoon_episode WHERE cartoon_id = #{cartoonId}")
    Episode selectById(String cartoonId);
}