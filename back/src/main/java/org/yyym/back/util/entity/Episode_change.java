package org.yyym.back.util.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Episode_change {
    public String cartoonId;
    public String m3u8_url;
    public Integer num;
    public String type;
}
