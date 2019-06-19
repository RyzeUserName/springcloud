package com.example.configclientbusrefresh.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * 描述
 * @author Ryze
 * @date 2019-06-19 15:53
 */
@Component
@RefreshScope
public class ConfigInfoProperties {
    @Value("${cn.springcloud.book.config}")
    private String config;

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }
}
