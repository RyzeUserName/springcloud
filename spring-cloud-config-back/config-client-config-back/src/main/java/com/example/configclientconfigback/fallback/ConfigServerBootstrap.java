package com.example.configclientconfigback.fallback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.config.client.ConfigClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author: zzf
 * @date: 2018/6/17
 * @time: 1:22
 * @description : 客户端自动配置依赖启动
 */

@Configuration
@EnableConfigurationProperties
@PropertySource(value = {"configClient.properties", "file:${spring.cloud.config.fallbackLocation:}/fallback.properties"}, ignoreResourceNotFound = true)
public class ConfigServerBootstrap {

    public static final String FALLBACK_FILE_NAME = "fallback.properties";

    @Value("${spring.cloud.config.fallbackLocation:}")
    private String fallbackLocation;
    @Autowired
    private ConfigClientProperties configClientProperties;

    @Bean
    public FallbackableConfigServicePropertySourceLocator fallbackableConfigServicePropertySourceLocator() {
        configClientProperties.setEnabled(false);
        FallbackableConfigServicePropertySourceLocator fallbackableConfigServicePropertySourceLocator
            = new FallbackableConfigServicePropertySourceLocator(configClientProperties, fallbackLocation);
        return fallbackableConfigServicePropertySourceLocator;
    }
}