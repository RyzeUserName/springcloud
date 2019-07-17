package com.example.configclientapollonew.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zunfa.zhong
 * @date: 2018-7-15 19:33:06
 */
@RestController
@RefreshScope
public class ConfigClientController {

    @Value("${config_info_new}")
    private String config;

    @RequestMapping("/getInfo")
    public String getConfigInfo() {
        return config;
    }
}