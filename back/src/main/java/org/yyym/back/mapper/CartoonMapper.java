package org.yyym.back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.yyym.back.util.entity.Cartoon;

@Mapper
public interface CartoonMapper extends BaseMapper<Cartoon> {
    @Select("SELECT cartoon_name FROM cartoon WHERE cartoon_id = #{cartoonId}")
    String selectNameById(String cartoonId);
}
