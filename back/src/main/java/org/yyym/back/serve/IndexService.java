package org.yyym.back.serve;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yyym.back.mapper.multi.CarouselMapper;
import org.yyym.back.mapper.CartoonMapper;
import org.yyym.back.mapper.multi.Category_sMapper;
import org.yyym.back.util.entity.Category_s;
import org.yyym.back.util.entity.Tag;
import org.yyym.back.util.helper.Result;

import java.util.ArrayList;
import java.util.List;

@Service
public class IndexService {
    @Autowired
    private CartoonMapper indexMapper;
    @Autowired
    private CarouselMapper carouselMapper;
    @Autowired
    private Category_sMapper categoryMapper;
    @Autowired
    private CartoonMapper cartoonMapper;

    public Result getMenu() {
        return Result.success( cartoonMapper.selectAll() );
    }
    public Result getCarousel() {
        return Result.success( carouselMapper.selectList(null) );
    }

    public Result getTagMenu(List<Tag> choices) {
        if(choices.isEmpty()) {
            return Result.success(cartoonMapper.selectAll());
        }
        if(choices.size() == 1 && choices.get(0).getBelong().equals("pay")) {
            return Result.success(cartoonMapper.selectAll());
        }
        return Result.success(categoryMapper.selectCartoonIdsByTags(choices));
    }
}
