package com.example.projectA.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @GetMapping("/testA")
    public String testA(){
        return "输出testA";
    }
}
