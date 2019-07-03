package com.example.configclientmultiple.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 描述
 * @author Ryze
 * @date 2019-07-03 10:10
 */
@Configuration
@ConfigurationProperties(prefix = "cn.springcloud.book")
public class ConfigInfoProperties {
    private String config;

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }
}
