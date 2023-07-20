package com.example.sso.controller;

import com.example.sso.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sso")
public class AuthController {
    @Autowired
    private LoginService loginService;
    @GetMapping("/login")
    public String login(){
        return "/login";
    }

    /**
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public String login(String username,String password){
        return loginService.login(username,password);
    }
    /**
     * 验证JWT
     * @param token token
     * @return 校验jwt是否合法
     */
    @RequestMapping("/checkJwt")
    @ResponseBody
    public boolean checkJwt(String token){
        return loginService.checkJWT(token);
    }

}
