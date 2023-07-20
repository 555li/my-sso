package com.example.sso.service;

import com.auth0.jwt.algorithms.Algorithm;
import com.example.sso.bean.JwtProperties;
import com.example.sso.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return token值
     */
    public String login(String username, String password) {
        if("".equals(username) || "".equals(password)){
            throw new RuntimeException("用户名或密码不能为空");
        }
        //为了测试方便，不去数据库比较密码
        if("123".equals(password)){
            //返回生成的token
            return jwtUtil.sign(username);
        }
        return null;
    }
    /**
     * 校验jwt是否成功
     * @param token token
     * @return 校验是否超过
     */
    public boolean checkJWT(String token){
        return jwtUtil.verify(token);
    }
}
