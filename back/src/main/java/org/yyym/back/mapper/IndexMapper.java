package org.yyym.back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.yyym.back.util.Cartoon;

@Mapper
public interface IndexMapper extends BaseMapper<Cartoon> {
}
