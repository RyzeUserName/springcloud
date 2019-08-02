package com.example.gateway.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * gateway filter 用于统计某个或者某种路由的处理时长
 * @author Ryze
 * @date 2019-08-02 10:30
 */
public class CustomGatewayFilter implements GatewayFilter, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(CustomGatewayFilter.class);
    private static final String COUNT_START_TIME = "countStartTime";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange.getAttributes().put(COUNT_START_TIME, System.currentTimeMillis());
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            Long start = exchange.getAttribute(COUNT_START_TIME);
            long keep = System.currentTimeMillis() - start;
            if (start != null) {
                logger.info(exchange.getRequest().getURI().getRawPath() + " : " + keep + " ms");
            }
        }));
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
