package org.yyym.back.util.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterInfo {
    private String username;
    private String email;
    private String password;
    private String re_password;
    private String confirmCode;
}
