package com.lft.providerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述
 * @author Ryze
 * @date 2019/3/20 20:27
 */
@RestController
public class TestController {
    @Autowired
    ConsumerService consumerService;

    @GetMapping("/test")
    public String getUser() {
        return consumerService.getHelloServiceData();
    }
}
