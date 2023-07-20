package com.example.sso.bean;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jwt")
@Getter
@Setter
public class JwtProperties {
    /**
     * 过期时间-分钟
     */
    private Integer expireTime;
    /**
     * 密钥
     */
    private String secret;
}
