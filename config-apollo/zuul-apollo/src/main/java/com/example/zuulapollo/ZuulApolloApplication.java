package com.example.zuulapollo;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableApolloConfig
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class ZuulApolloApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApolloApplication.class, args);
    }

}
