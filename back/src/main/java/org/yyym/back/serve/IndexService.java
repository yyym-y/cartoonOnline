package org.yyym.back.serve;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yyym.back.mapper.CarouselMapper;
import org.yyym.back.mapper.CartoonMapper;
import org.yyym.back.util.Result;

@Service
public class IndexService {
    @Autowired
    private CartoonMapper indexMapper;
    @Autowired
    private CarouselMapper carouselMapper;

    public Result getMenu() {
        return Result.success( indexMapper.selectList(null) );
    }
    public Result getCarousel() {
        return Result.success( carouselMapper.selectList(null) );
    }
}
