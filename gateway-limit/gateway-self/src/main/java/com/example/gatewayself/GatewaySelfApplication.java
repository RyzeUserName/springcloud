package com.example.gatewayself;

import com.example.gatewayself.config.RemoteAddrKeyResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewaySelfApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewaySelfApplication.class, args);
    }
    @Bean(name = RemoteAddrKeyResolver.BEAN_NAME)
    public RemoteAddrKeyResolver remoteAddrKeyResolver() {
        return new RemoteAddrKeyResolver();
    }


}
