package com.lft.collapser.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.Controller;

/**
 * 把拦截器加入spring
 * @author Ryze
 * @date 2019-03-21 18:27
 */
@Configuration
public class CacheConfiguration {
    @Bean
    @ConditionalOnClass(Controller.class)
    public CacheContextInterceptor userContextInterceptor() {
        return new CacheContextInterceptor();
    }

    @Configuration
    @ConditionalOnClass(Controller.class)
    public class WebMvcConfig implements WebMvcConfigurer {
        @Autowired
        CacheContextInterceptor userContextInterceptor;

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(userContextInterceptor);
        }
    }
}
