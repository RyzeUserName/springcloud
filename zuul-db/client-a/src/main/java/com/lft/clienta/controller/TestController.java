package com.lft.clienta.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * add
 * @author Ryze
 * @date 2019-03-28 14:52
 */
@RestController
public class TestController {

    @GetMapping("/add")
    public int add(@RequestParam("a") int a, @RequestParam("b") int b) {
        return a + b;
    }
}
