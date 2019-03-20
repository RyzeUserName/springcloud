package com.lft.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class ZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
        // cd E:\study\springcloud\eureka-2\RemoteRegion\zuul
        // mvn spring-boot:run -Dspring.profiles.active=zone1
        // mvn spring-boot:run -Dspring.profiles.active=zone2


        //http://localhost:10001/client/actuator/env
        //http://localhost:10002/client/actuator/env

        //http://localhost:8071/actuator/env
        //http://localhost:8072/actuator/env
        //http://localhost:8073/actuator/env
        //http://localhost:8074/actuator/env
    }

}
