package org.yyym.back.util.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("play_infos")
public class PlayInfo {
    private String cartoonId;
    private Integer year;
    private Integer month;
    private Integer playTime;
    @TableField(exist = false)
    private Cartoon cartoon;
}
