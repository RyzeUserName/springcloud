package com.example.configclient.controller;

import com.example.configclient.config.ConfigInfoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述
 * @author Ryze
 * @date 2019-07-03 10:13
 */
@RestController
public class TestController {
    @Autowired
    private ConfigInfoProperties configInfoProperties;

    @GetMapping("/getInfo")
    public String getInfo() {
        System.out.println("1111");
        return configInfoProperties.getConfig();
    }

}
