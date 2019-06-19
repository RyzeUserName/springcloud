package com.example.configclientbusrefresh.controller;

import com.example.configclientbusrefresh.config.ConfigInfoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述
 * @author Ryze
 * @date 2019-06-19 14:54
 */
@RefreshScope
@RestController
@RequestMapping("/config")
public class ConfigClientController {
    @Autowired
    private ConfigInfoProperties configInfoProperties;
    @GetMapping("/info")
    public String getInfo() {
        return configInfoProperties.getConfig();
    }

}
