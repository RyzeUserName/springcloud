package com.example.configclientback.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述
 * @author Ryze
 * @date 2019-07-03 10:13
 */
@RestController
public class TestController {
    @Value("${cn.springcloud.book.config}")
    private String config;

    @GetMapping("/getInfo")
    public String getInfo() {
        System.out.println("1111");
        return config;
    }

}
