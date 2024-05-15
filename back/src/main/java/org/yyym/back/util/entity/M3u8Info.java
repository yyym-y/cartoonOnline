package org.yyym.back.util.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("m3u8_info")
public class M3u8Info {
    @TableId("cartoon_id")
    public String cartoonId;
    public Integer num;
    public String m3u8Url;
}