package org.yyym.back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.yyym.back.util.entity.Description;

@Mapper
public interface DescriptionMapper extends BaseMapper<Description> {

    @Select("select descrip from descrip where cartoon_id =  #{cartoonId}")
    public String selectDescrip(String cartoonId);
}
