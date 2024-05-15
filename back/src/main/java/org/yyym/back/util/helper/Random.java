package org.yyym.back.util.helper;

import java.util.UUID;

public class Random {
    public static String generateName(boolean bool){
        if(bool) return UUID.randomUUID().toString();
        else return generateName();
    }

    public static String generateName(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}