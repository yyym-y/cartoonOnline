package org.yyym.back.mapper.multi;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.yyym.back.util.entity.Category_s;
import org.yyym.back.util.entity.Tag;

import java.util.List;
import java.util.Map;

@Mapper
public interface Category_sMapper extends BaseMapper<Category_s> {
    @Select({
            "<script>",
                "SELECT distinct category.cartoon_id FROM category LEFT JOIN tag_group ",
                "ON category.tag_id = tag_group.tag_id WHERE ",
                "<foreach item='item' index='index' collection='list' separator=' OR '>",
                    "(tag_group.type = #{item.belong} AND tag_group.name = #{item.name})",
                "</foreach>",
            "</script>"
    })
    @Results({
            @Result(column = "cartoon_id", property = "cartoonId"),
            @Result(column = "tag_id", property = "tagId"),
            @Result(column = "cartoon_id", property = "cartoon",
                    one = @One(select = "org.yyym.back.mapper.CartoonMapper.selectById")),
            @Result(column = "cartoon_id", property = "playTime",
                    one = @One(select = "org.yyym.back.mapper.multi.PlayInfoMapper.getExtraPlayTime")),
    })
    List<Category_s> selectCartoonIdsByTags(@Param("list") List<Tag> list);


    @Select("select tag_group.tag_id, tag_group.type, tag_group.name " +
            "from tag_group " +
            "left join category on tag_group.tag_id = category.tag_id " +
            "where category.cartoon_id = #{cartoonId}")
    List<Map<String, Object>> selectCartoonTag(String cartoonId);
}
