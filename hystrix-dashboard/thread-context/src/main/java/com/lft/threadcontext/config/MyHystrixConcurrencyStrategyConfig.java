package com.lft.threadcontext.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 描述
 * @author Ryze
 * @date 2019/3/21 23:50
 */
@Configuration
public class MyHystrixConcurrencyStrategyConfig {
    @Bean
    public MyHystrixConcurrencyStrategy springCloudHystrixConcurrencyStrategy() {
        return new MyHystrixConcurrencyStrategy();
    }
}
