package com.example.clienta.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述
 * @author Ryze
 * @date 2019-03-22 14:56
 */
@RestController
public class HelloController {
    @GetMapping("/add")
    public Integer add(Integer a, Integer b) {
        return a + b;
    }
}
