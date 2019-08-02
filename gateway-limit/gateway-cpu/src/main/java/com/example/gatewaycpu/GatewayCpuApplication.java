package com.example.gatewaycpu;

import com.example.gatewaycpu.config.GatewayRateLimitFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.Duration;

@SpringBootApplication
public class GatewayCpuApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayCpuApplication.class, args);
    }

    @Autowired
    private GatewayRateLimitFilter gatewayRateLimitFilter;

    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes().route(r -> r.path("/test").filters(f -> f.filter(gatewayRateLimitFilter))
            .uri("http://localhost:8000/test").id("rate_limit_route")).build();
    }
}
