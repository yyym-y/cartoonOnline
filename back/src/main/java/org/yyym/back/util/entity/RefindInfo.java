package org.yyym.back.util.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefindInfo {
    private String email;
    private String confirmCode;
    private String password;
    private String re_password;
}
