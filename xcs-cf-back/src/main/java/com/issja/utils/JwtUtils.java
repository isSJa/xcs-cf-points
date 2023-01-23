package com.issja.utils;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.UUID;

/**
 * @Auth ： isSJa
 * @Time ： 2023/1/23 23:33
 **/
public class JwtUtils {
    // 签名
    private static String signature = "isSJa";
    // 有效期
    private static long time=1000*60*60*24;
    // 生成token
    public static String createToken(Integer id,String username){
        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder
                // header
                .setHeaderParam("typ","JWT")    //type
                .setHeaderParam("alg","HS256")  //算法
                // payload
                .claim("id",id)
                .claim("username",username)
                .setSubject("xcs-cf-points")                 //主题
                .setExpiration(new Date(System.currentTimeMillis()+time))
                .setId(UUID.randomUUID().toString())
                // signature
                .signWith(SignatureAlgorithm.HS256,signature)
                // 拼接
                .compact();
        return jwtToken;
    }
    // 校验token
    public static boolean checkToken(String token){
        if(token==null)return false;
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(signature).parseClaimsJws(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
