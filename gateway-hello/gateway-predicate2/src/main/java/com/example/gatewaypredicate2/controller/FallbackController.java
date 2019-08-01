package com.example.gatewaypredicate2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述
 * @author Ryze
 * @date 2019-08-01 19:20
 */
@RestController
public class FallbackController {
    @GetMapping("/fallback")
    public String fallback(){
        return "gateway  fallback";
    }
}
