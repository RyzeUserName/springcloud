package com.lft.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class, args);
        //cd E:\study\springcloud\eureka-2\OnlineExpansion\eureka
        //mvn spring-boot:run -Dspring.profiles.active=peer1
        //mvn spring-boot:run -Dspring.profiles.active=peer2
        //mvn spring-boot:run -Dspring.profiles.active=peer3
    }

}
