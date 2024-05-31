package org.yyym.back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.yyym.back.util.entity.Order;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
