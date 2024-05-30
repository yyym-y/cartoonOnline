package org.yyym.back.util.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("category")
public class Category_s {
    @TableField("cartoon_id")
    private String cartoonId;
    @TableField("tag_id")
    private String tagId;
    @TableField(exist = false)
    private Cartoon cartoon;
    @TableField(exist = false)
    private Integer playTime;
}
