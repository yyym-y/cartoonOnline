package org.yyym.back.mapper.multi;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.yyym.back.util.entity.Carousel;

import java.util.List;

@Mapper
public interface CarouselMapper extends BaseMapper<Carousel> {
    @Select("SELECT * FROM carousel")
    @Results({
            @Result(column = "cartoon_id", property = "cartoonId"),
            @Result(column = "cartoon_id", property = "cartoon",
                one = @One(select = "org.yyym.back.mapper.CartoonMapper.selectById"))
    })
    List<Carousel> selectList();
}
