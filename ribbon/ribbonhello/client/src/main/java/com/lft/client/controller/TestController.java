package com.lft.client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 描述
 * @author Ryze
 * @date 2019-03-18 14:35
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/add")
    public String add(@RequestParam("a") int a, @RequestParam("b") int b, HttpServletRequest request) {
        return "FROM PORT " + request.getServerPort() + " RESULT " + a + b;
    }
}
