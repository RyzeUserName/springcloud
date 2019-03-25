package com.example.zuul.config;

import com.example.zuul.filter.FirstPreFilter;
import com.example.zuul.filter.PostPreFilter;
import com.example.zuul.filter.SecondPreFilter;
import com.example.zuul.filter.ThirdPreFilter;
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

    @Bean
    public SecondPreFilter secondPreFilter() {
        return new SecondPreFilter();
    }

    @Bean
    public ThirdPreFilter thirdPreFilter() {
        return new ThirdPreFilter();
    }
    @Bean
    public PostPreFilter postPreFilter() {
        return new PostPreFilter();
    }

}
