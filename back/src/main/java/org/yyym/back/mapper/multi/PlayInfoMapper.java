package org.yyym.back.mapper.multi;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.yyym.back.util.entity.PlayInfo;

import java.util.List;

@Mapper
public interface PlayInfoMapper extends BaseMapper<PlayInfo> {
    @Select("SELECT * FROM play_infos")
    @Results({
            @Result(column = "cartoon_id", property = "cartoonId"),
            @Result(column = "cartoon_id", property = "cartoon",
                    one = @One(select = "org.yyym.back.mapper.CartoonMapper.selectById"))
    })
    List<PlayInfo> selectList();
    @Select("SELECT cartoon_id, sum(play_time) as playTime FROM play_infos GROUP BY cartoon_id")
    @Results({
            @Result(column = "cartoon_id", property = "cartoonId"),
            @Result(column = "playTime", property = "playTime"),
            @Result(column = "cartoon_id", property = "cartoon",
                    one = @One(select = "org.yyym.back.mapper.CartoonMapper.selectById"))
    })
    List<PlayInfo> getAllPlayData();

    @Select("SELECT cartoon_id, year, month, sum(play_time) as playTime "
            + "FROM play_infos WHERE year = #{year} AND month = #{month} GROUP BY cartoon_id")
    @Results({
            @Result(column = "cartoon_id", property = "cartoonId"),
            @Result(column = "playTime", property = "playTime"),
            @Result(column = "cartoon_id", property = "cartoon",
                    one = @One(select = "org.yyym.back.mapper.CartoonMapper.selectById"))
    })
    List<PlayInfo> getMonthPlayData(String year, String month);

    @Select("SELECT cartoon_id, year, sum(play_time) as playTime "
            + "FROM play_infos WHERE year = #{year} GROUP BY cartoon_id")
    @Results({
            @Result(column = "cartoon_id", property = "cartoonId"),
            @Result(column = "playTime", property = "playTime"),
            @Result(column = "cartoon_id", property = "cartoon",
                    one = @One(select = "org.yyym.back.mapper.CartoonMapper.selectById"))
    })
    List<PlayInfo> getYearPlayData(String year);

    @Select("SELECT sum(play_time) as playTime FROM play_infos WHERE cartoon_id = #{cartoonId}")
    Integer getExtraPlayTime(@Param("cartoonId") String cartoonId);
}
