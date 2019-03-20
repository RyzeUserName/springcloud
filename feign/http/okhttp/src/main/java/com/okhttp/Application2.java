package com.okhttp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class Application2 {

    public static void main(String[] args) {
        SpringApplication.run(Application2.class, args);
        //http://localhost:8010/search/repositories?str=spring-cloud-dubbo
    }

}
