package com.example.configclient.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 描述
 * @author Ryze
 * @date 2019/4/24 22:47
 */
@ConfigurationProperties("cn.springcloud.book")
@Component
public class ConfigInfo {
    private String config;

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }
}
