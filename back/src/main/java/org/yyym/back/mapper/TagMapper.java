package org.yyym.back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.yyym.back.util.entity.Tag;

@Mapper
public interface TagMapper extends BaseMapper<Tag> {
}
