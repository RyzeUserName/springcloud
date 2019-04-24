package com.example.configclient.controller;

import com.example.configclient.config.ConfigInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述
 * @author Ryze
 * @date 2019/4/24 22:49
 */
@RefreshScope
@RestController
public class Controller {
    @Autowired
    ConfigInfo configInfo;

    @GetMapping("/configInfo")
    public String configInfo() {
        return configInfo.getConfig();
    }
}
