package com.example.gatewaybucket4j;

import com.example.gatewaybucket4j.filter.GatewayRateLimitFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.Duration;

@SpringBootApplication
public class GatewayBucket4jApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayBucket4jApplication.class, args);
    }

    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes().route(r -> r.path("/test").filters(f -> f.filter(new GatewayRateLimitFilter(10, 1, Duration.ofSeconds(1))))
            .uri("http://localhost:8000/test").id("rate_limit_route")).build();
    }

}
