package com.lft.loadbalance.loadBalanceConfig;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 描述
 * @author Ryze
 * @date 2019-03-18 17:50
 */
@Configuration
@AvoidScan
public class TestConfig {
    @Autowired
    IClientConfig config;

    @Bean
    public IRule ribbonRule(IClientConfig config) {
        return new RandomRule();
    }
}
