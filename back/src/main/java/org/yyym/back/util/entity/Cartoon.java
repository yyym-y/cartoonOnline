package org.yyym.back.util.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("cartoon")
public class Cartoon {
    @TableId("cartoon_id")
    public String cartoonId;
    public String cartoonName;
    public String cartoonCover;
    public Integer cartoonPermit;
}
