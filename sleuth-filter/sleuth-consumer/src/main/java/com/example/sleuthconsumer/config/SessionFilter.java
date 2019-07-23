package com.example.sleuthconsumer.config;

import brave.propagation.ExtraFieldPropagation;
import org.springframework.cloud.sleuth.instrument.web.SleuthWebProperties;
import org.springframework.cloud.sleuth.instrument.web.TraceWebServletAutoConfiguration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * 自定义过滤器
 * 获取当前的SessionId,放入Baggage
 * 对一些请求跳过执行
 * @author Ryze
 * @date 2019-07-23 18:49
 */
@Component
@Order(TraceWebServletAutoConfiguration.TRACING_FILTER_ORDER + 1)
public class SessionFilter extends GenericFilterBean {
    private Pattern compile = Pattern.compile(SleuthWebProperties.DEFAULT_SKIP_PATTERN);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        if (!(servletRequest instanceof HttpServletRequest) || !(servletResponse instanceof HttpServletResponse)) {
            throw new ServletException("这个过滤器 只过滤 http 请求");
        }
        HttpServletRequest servletRequest1 = (HttpServletRequest) servletRequest;
        boolean matches = compile.matcher(servletRequest1.getRequestURI()).matches();
        if (!matches) {
            //将 sessionId 放入Baggage
            ExtraFieldPropagation.set("SessionId", ((HttpServletRequest) servletRequest).getSession().getId());
        }
        filterChain.doFilter(servletRequest, servletResponse);

    }
}
