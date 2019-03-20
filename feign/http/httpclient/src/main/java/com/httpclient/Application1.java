package com.httpclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class Application1 {

    public static void main(String[] args) {
        SpringApplication.run(Application1.class, args);
        //http://localhost:8011/search/repositories?str=spring-cloud-dubbo
    }

}
