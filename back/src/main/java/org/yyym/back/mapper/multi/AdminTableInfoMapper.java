package org.yyym.back.mapper.multi;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.yyym.back.util.entity.AdminTableInfo;

import java.util.List;

@Mapper
public interface AdminTableInfoMapper extends BaseMapper<AdminTableInfo> {

    @Select("select * from cartoon")
    @Results({
            @Result(column = "cartoon_id", property = "cartoonId"),
            @Result(column = "cartoon_id", property = "episode",
                one = @One(select = "org.yyym.back.mapper.multi.EpisodeMapper.selectNum")),
            @Result(column = "cartoon_id", property = "discrip",
                one = @One(select = "org.yyym.back.mapper.DescriptionMapper.selectDescrip")),
            @Result(column = "cartoon_id", property = "discrip",
                    one = @One(select = "org.yyym.back.mapper.DescriptionMapper.selectDescrip")),
            @Result(column = "cartoon_id", property = "tags",
                    many = @Many(select = "org.yyym.back.mapper.multi.Category_sMapper.selectCartoonTag")),
            @Result(column = "cartoon_id", property = "episodeInfos",
                    one = @One(select = "org.yyym.back.mapper.multi.EpisodeMapper.selectEpisode")),
    })
    List<AdminTableInfo> selectAllInfos();

}
