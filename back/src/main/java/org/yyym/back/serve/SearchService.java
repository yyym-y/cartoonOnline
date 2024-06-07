package org.yyym.back.serve;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yyym.back.mapper.CartoonMapper;
import org.yyym.back.mapper.DescriptionMapper;
import org.yyym.back.util.entity.Cartoon;
import org.yyym.back.util.entity.Description;
import org.yyym.back.util.helper.Result;

import java.util.HashMap;
import java.util.List;

@Service
public class SearchService {
    @Autowired
    private CartoonMapper cartoonMapper;
    @Autowired
    private DescriptionMapper descriptionMapper;

    public Result search(String input) {
        HashMap<String, List<Cartoon>> res = new HashMap<>();
        boolean isFind = false;
        List<Cartoon> findByName = cartoonMapper.selectList(
                new QueryWrapper<Cartoon>().like("cartoon_name", input));
        if(findByName != null && ! findByName.isEmpty()) isFind = true;
        List<Cartoon> findByEditor = cartoonMapper.selectEdit(input);
        if(findByEditor != null && ! findByEditor.isEmpty()) isFind = true;
        if(! isFind)
            return Result.error("do find any useful info");
        res.put("cartoon_name", findByName);
        res.put("director", findByEditor);
        return Result.success(res);
    }

    public Result getDescrip(String cartoonId) {
        return Result.success(descriptionMapper.selectById(cartoonId));
    }
}
