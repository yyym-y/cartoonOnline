package org.yyym.back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.yyym.back.util.entity.Cartoon;
import org.yyym.back.util.entity.Category_s;

import java.util.List;

@Mapper
public interface CartoonMapper extends BaseMapper<Cartoon> {
    @Select("SELECT cartoon_name FROM cartoon WHERE cartoon_id = #{cartoonId}")
    String selectNameById(String cartoonId);

    @Select("SELECT cartoon_id FROM cartoon")
    @Results({@Result(column = "cartoon_id", property = "cartoonId"),
            @Result(column = "cartoon_id", property = "cartoon",
                    one = @One(select = "org.yyym.back.mapper.CartoonMapper.selectById")),
            @Result(column = "cartoon_id", property = "playTime",
                    one = @One(select = "org.yyym.back.mapper.multi.PlayInfoMapper.getExtraPlayTime")),
    })
    List<Category_s> selectAll();

    @Select("SELECT DISTINCT * FROM cartoon LEFT JOIN category " +
            "ON cartoon.cartoon_id = category.cartoon_id " +
            "LEFT JOIN tag_group ON category.tag_id = tag_group.tag_id " +
            "WHERE tag_group.type = 'editor' AND tag_group.name LIKE concat('%', #{input}, '%');")
    List<Cartoon> selectEdit(String input);
}
