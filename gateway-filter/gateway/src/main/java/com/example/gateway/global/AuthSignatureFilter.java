package com.example.gateway.global;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 全局Filter 从gateway 的全局上下文ServerWebExchange 中获取authToken
 * @author Ryze
 * @date 2019-08-02 10:52
 */
@Component
public class AuthSignatureFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String authToken = exchange.getRequest().getQueryParams().getFirst("authToken");
        if (StringUtils.isEmpty(authToken)) {
            //401 未授权
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -400;
    }
}
