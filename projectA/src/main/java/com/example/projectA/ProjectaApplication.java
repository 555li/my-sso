package com.example.projectA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class ProjectaApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProjectaApplication.class, args);
    }
}
