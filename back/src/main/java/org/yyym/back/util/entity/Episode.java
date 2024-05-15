package org.yyym.back.util.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("cartoon_episode")
public class Episode {
    @TableId("cartoon_id")
    public String cartoonId;
    public Integer num;
    @TableField(exist = false)
    public List<M3u8Info> infos;
}
