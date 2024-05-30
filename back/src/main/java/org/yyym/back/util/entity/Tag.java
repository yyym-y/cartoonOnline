package org.yyym.back.util.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tag_group")
public class Tag {
    @TableId("tag_id")
    private Integer tagId;
    @TableField("type")
    private String belong;
    @TableField("name")
    private String name;
}
