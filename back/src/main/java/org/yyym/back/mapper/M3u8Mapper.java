package org.yyym.back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.yyym.back.util.M3u8Info;

import java.util.List;

@Mapper
public interface M3u8Mapper extends BaseMapper<M3u8Info> {
    @Select("SELECT * FROM m3u8_info WHERE cartoon_id = #{cartoonId}")
    List<M3u8Info> selectById(String cartoonId);
}
