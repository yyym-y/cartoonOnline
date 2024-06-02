package org.yyym.back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.yyym.back.util.entity.Tag;

import java.util.List;

@Mapper
public interface TagMapper extends BaseMapper<Tag> {
    @Select("select * from tag_group where tag_id = #{tagId}")
    Tag selectByTagId(Integer tagId);
}
