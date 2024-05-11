package org.yyym.back.serve;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.sf.jsqlparser.statement.create.table.Index;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yyym.back.mapper.IndexMapper;
import org.yyym.back.util.Cartoon;
import org.yyym.back.util.Result;

@Service
public class IndexService {
    @Autowired
    private IndexMapper indexMapper;

    public Result getMenu() {
        return Result.success( indexMapper.selectList(null) );
    }
}
