package org.yyym.back.util.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("carousel")
public class Carousel {
    @TableId("cartoon_id")
    public String cartoonId;
    public String carouselCover;
    @TableField(exist = false)
    public Cartoon cartoon;
}
