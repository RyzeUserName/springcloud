package com.example.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;

/**
 * 描述
 * @author Ryze
 * @date 2019-08-02 14:53
 */
@Component
public class HttpsToHttpAfterLoadBalance implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        Object o = exchange.getAttributes().get(GATEWAY_REQUEST_URL_ATTR);
        if (o != null) {
            URI o1 = (URI) o;
            URI http = this.upgradeConnection(o1, "http");
            exchange.getAttributes().put(GATEWAY_REQUEST_URL_ATTR, http);
        }
        return chain.filter(exchange);
    }

    private URI upgradeConnection(URI uri, String scheme) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUri(uri).scheme(scheme);
        if (uri.getRawQuery() != null) {
            // When building the URI, UriComponentsBuilder verify the allowed characters and does not
            // support the '+' so we replace it for its equivalent '%20'.
            // See issue https://jira.spring.io/browse/SPR-10172
            uriComponentsBuilder.replaceQuery(uri.getRawQuery().replace("+", "%20"));
        }
        return uriComponentsBuilder.build(true).toUri();
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
        return 10101;
    }
}
