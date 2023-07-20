package com.example.sso.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.sso.bean.JwtProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;
import java.util.Date;

@Component
public class JwtUtil {
    @Autowired
    private JwtProperties jwtProperties;
    public String  sign(String username){
        Algorithm algorithm = Algorithm.HMAC256(jwtProperties.getSecret());
        return JWT.create()
                //设置过期时间1个小时
                .withExpiresAt(new Date(System.currentTimeMillis()+jwtProperties.getExpireTime()*60*1000))
                //设置负载
                .withClaim("username",username).sign(algorithm);
    }

    public static void main(String[] args) {
        Algorithm algorithm = Algorithm.HMAC256("MySuperSecretKey123!@#");
        String username = "admin";
        String token = JWT.create()
                //设置过期时间1个小时
                .withExpiresAt(new Date(System.currentTimeMillis()+60*60*1000))
                //设置负载
                .withClaim("username",username).sign(algorithm);
        System.out.println(token);
    }
    /**
     * 校验token是否正确
     */
    public boolean  verify(String token){
        if(token==null||token.length()==0){
            throw new RuntimeException("token不能为空");
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtProperties.getSecret());
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
