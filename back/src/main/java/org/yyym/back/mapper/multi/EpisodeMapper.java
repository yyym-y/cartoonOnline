package org.yyym.back.mapper.multi;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.yyym.back.util.entity.Episode;

import java.util.List;
import java.util.Map;

@Mapper
public interface EpisodeMapper extends BaseMapper<Episode> {
    @Results({
            @Result(column = "cartoon_id", property = "cartoonId"),
            @Result(column = "cartoon_id", property = "infos",
                    many = @Many(select = "org.yyym.back.mapper.M3u8Mapper.selectById"))
    })
    @Select("SELECT * FROM cartoon_episode WHERE cartoon_id = #{cartoonId}")
    Episode selectById(String cartoonId);

    @Select("SELECT num FROM cartoon_episode WHERE cartoon_id = #{cartoonId}")
    Integer selectNum(String cartoonId);

    @Select("select num, m3u8_url from m3u8_info where cartoon_id = #{cartoonId}")
    List<Map<String, Object>> selectEpisode(String cartoonId);
}