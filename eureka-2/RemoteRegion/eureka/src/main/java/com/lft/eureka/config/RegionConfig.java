package com.lft.eureka.config;

import com.netflix.discovery.EurekaClientConfig;
import com.netflix.eureka.EurekaServerConfig;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.netflix.eureka.server.EurekaServerAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EurekaServerConfigBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * 描述
 * @author Ryze
 * @date 2019-03-13 10:34
 */
@Configuration
@AutoConfigureBefore(EurekaServerAutoConfiguration.class)
public class RegionConfig {
    @Bean
    @ConditionalOnMissingBean
    public EurekaServerConfig eurekaServerConfig(EurekaClientConfig eurekaClientConfig) {
        EurekaServerConfigBean eurekaServerConfigBean = new EurekaServerConfigBean();
        if (eurekaClientConfig.shouldRegisterWithEureka()) {
            eurekaServerConfigBean.setRegistrySyncRetries(5);
        }
        eurekaServerConfigBean.setRemoteRegionAppWhitelist(new HashMap<>());
        return eurekaServerConfigBean;
    }
}
