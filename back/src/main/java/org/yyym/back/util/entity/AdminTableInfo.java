package org.yyym.back.util.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("cartoon")
public class AdminTableInfo {
    @TableId("cartoon_id")
    public String cartoonId;
    public String cartoonName;
    public Integer cartoonPermit;

    @TableField(exist = false)
    public Integer episode;

    public String cartoonCover;

    @TableField(exist = false)
    public String discrip;
    @TableField(exist = false)
    public List<Map<String, Object>> tags;
    @TableField(exist = false)
    public List<Map<String, Object>> episodeInfos;
}
