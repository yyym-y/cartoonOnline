package org.yyym.back.util.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("descrip")
public class Description {
    @TableId("cartoon_id")
    public String cartoonId;
    public String descrip;
}
