package org.yyym.back.util.helper;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.Map;

public class Jwt {
    public static String getJwt(Map<String, Object> put, int time, String sign) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, sign.getBytes()) //签名 + 自定义字符串
                .setClaims(put)
                .setExpiration(new Date(System.currentTimeMillis() + (time * 1000L * 60))) // 设置过期时间
                .compact();
    }

    public static Result decodeJwt(String jwt, String sign){
        try{
            Object info = Jwts.parser()
                    .setSigningKey(sign.getBytes())
                    .parseClaimsJws(jwt)
                    .getBody();
            return Result.success(info);
        }catch (ExpiredJwtException e) {
            return Result.error("JWT 过期");
        } catch (UnsupportedJwtException e) {
            return Result.error("不支持的JWT");
        } catch (MalformedJwtException e) {
            return Result.error("JWT格式错误");
        } catch (SignatureException e) {
            return Result.error("签名异常");
        } catch (IllegalArgumentException e) {
            return Result.error("非法请求");
        } catch (Exception e) {
            return Result.error("解析异常：");
        }
    }
}
