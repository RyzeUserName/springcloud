package com.lft.eurekaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EurekaclientApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaclientApplication.class, args);
        // cd E:\study\springcloud\eureka-2\MultiZone\eurekaclient
        // mvn spring-boot:run -Dspring.profiles.active=zone1
        // mvn spring-boot:run -Dspring.profiles.active=zone2
    }

}
