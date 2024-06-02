package org.yyym.back.serve;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yyym.back.mapper.multi.AdminTableInfoMapper;
import org.yyym.back.util.helper.Result;

@Service
public class AdminService {
    @Autowired
    private AdminTableInfoMapper adminTableInfoMapper;

    public Result getAllTableInfos() {
        return Result.success(adminTableInfoMapper.selectAllInfos());
    }
}
