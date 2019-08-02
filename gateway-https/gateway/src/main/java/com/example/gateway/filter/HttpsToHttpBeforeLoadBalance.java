package com.example.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * https->转成http 在 LoadBalancerClientFilter 之前
 * @author Ryze
 * @date 2019-08-02 14:32
 */
//@Component
public class HttpsToHttpBeforeLoadBalance implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        URI uri = exchange.getRequest().getURI();
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpRequest.Builder mutate = request.mutate();
        String stringUri = request.getURI().toString();
        if (stringUri != null && stringUri.startsWith("https")) {
            URI http = null;
            try {
                http = new URI("http", uri.getUserInfo(), uri.getHost(), uri.getPort(), uri.getPath(), uri.getQuery(), uri.getFragment());
                mutate.uri(http);
            } catch (URISyntaxException e) {
                throw new IllegalStateException(e.getMessage(), e);
            }
        }
        ServerHttpRequest build = mutate.build();

        return chain.filter(exchange.mutate().request(build).build());
    }

    /**
     * LoadBalancerClientFilter 是10100
     * @param
     * @return int
     * @author Ryze
     * @date 2019-08-02 14:33:18
     */
    @Override
    public int getOrder() {
        return 10099;
    }
}
