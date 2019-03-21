package com.lft.hystrixcache.config;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 初始化上下文
 * @author Ryze
 * @date 2019-03-21 17:56
 */

public class CacheContextInterceptor implements HandlerInterceptor {
    HystrixRequestContext hystrixRequestContext;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        hystrixRequestContext = HystrixRequestContext.initializeContext();
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        hystrixRequestContext.shutdown();
    }
}
