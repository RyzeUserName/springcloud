package com.example.clienta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ClientAApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientAApplication.class, args);
    }

}
