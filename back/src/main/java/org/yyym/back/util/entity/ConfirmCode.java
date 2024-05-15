package org.yyym.back.util.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("code_tem")
public class ConfirmCode {
    public String email;
    public String code;
    public Date time;
}
