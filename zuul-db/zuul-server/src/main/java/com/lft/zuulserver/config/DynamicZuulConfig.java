package com.lft.zuulserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 描述
 * @author Ryze
 * @date 2019-03-28 19:06
 */
@Configuration
public class DynamicZuulConfig {
    @Autowired
    private ZuulProperties zuulProperties;
    @Autowired
    private ServerProperties serverProperties;

    @Bean
    public DynamicZuulRouteLocator dynamicZuulRouteLocator() {
        return new DynamicZuulRouteLocator(serverProperties.getServlet().getServletPrefix(), zuulProperties);
    }
}
