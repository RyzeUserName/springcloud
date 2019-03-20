package com.lft.loadbalance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 描述
 * @author Ryze
 * @date 2019-03-18 14:49
 */
@RestController
public class TestController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/add")
    public String add(Integer a, Integer b) {
        String result = restTemplate.getForObject("http://CLIENT/test/add?a=" + a + "&b=" + b, String.class);
        System.out.println(result);
        return result;
    }
}
