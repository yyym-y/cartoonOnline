package org.yyym.back.serve;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yyym.back.mapper.multi.PlayInfoMapper;
import org.yyym.back.util.entity.Cartoon;
import org.yyym.back.util.entity.PlayInfo;
import org.yyym.back.util.helper.Result;

import java.util.Calendar;
import java.util.List;

@Service
public class DataService {
    @Autowired
    private PlayInfoMapper playInfoMapper;
    public Result getPlayLineData() {
        return Result.success(playInfoMapper.selectList());
    }

    public Result getAllPlayData() {
        List<PlayInfo> res = playInfoMapper.getAllPlayData();
        return Result.success(res);
    }

    public Result getMonthPlayData() {
        Calendar instance = Calendar.getInstance();
        int year = instance.get(Calendar.YEAR);
        int month = instance.get(Calendar.MONTH) + 1;

        List<PlayInfo> res = playInfoMapper.getMonthPlayData(Integer.toString(year), Integer.toString(month));
        if(res == null || res.isEmpty())
            return Result.error("playInfo is empty");
        return Result.success(res);
    }

    public Result getYearPlayData(String year) {
        List<PlayInfo> res = playInfoMapper.getYearPlayData(year);
        if(res == null) return Result.error("playInfo is empty");
        return Result.success(res);
    }
}
