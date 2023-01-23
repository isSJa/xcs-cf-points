package com.issja;

import io.jsonwebtoken.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.UUID;

@SpringBootTest
class XcsCfBackApplicationTests {

    // 签名
    private static String signature = "isSJa";
    // 有效期
    private static long time=1000*60*60*24;

    // 加密
    @Test
    void jwt() {
        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder
                // header
                .setHeaderParam("typ","JWT")    //type
                .setHeaderParam("alg","HS256")  //算法
                // payload
                .claim("username","isSJa")
                .setSubject("jwt-test")                 //主题
                .setExpiration(new Date(System.currentTimeMillis()+time))
                .setId(UUID.randomUUID().toString())
                // signature
                .signWith(SignatureAlgorithm.HS256,signature)
                // 拼接
                .compact();
        System.out.println(jwtToken);
    }


    @Test
    // 解密
    void parse(){
        String token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6ImlzU0phIiwic3ViIjoiand0LXRlc3QiLCJleHAiOjE2NzQ1NzM0MTEsImp0aSI6IjUwNDE0ZGY5LWRjZjItNDg0YS1hMGIwLTdiNzQ4ODg5NjQ3MiJ9.GVGCTQg1TMN8EEL0SXE0iRNZu4HAyxVWXRD7QpN3lNM";
        JwtParser jwtParser=Jwts.parser();
        Jws<Claims> claimsJws = jwtParser.setSigningKey(signature)
                .parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        System.out.println(claims.get("username"));
        System.out.println(claims.getSubject());
        System.out.println(claims.getExpiration());
        System.out.println(claims.getId());
    }
}
