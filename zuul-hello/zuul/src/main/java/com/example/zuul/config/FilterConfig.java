package com.example.zuul.config;

import com.example.zuul.filter.FirstPreFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 描述
 * @author Ryze
 * @date 2019-03-25 9:31
 */
@Configuration
public class FilterConfig {
    @Bean
    public FirstPreFilter firstPreFilter() {
        return new FirstPreFilter();
    }

}
