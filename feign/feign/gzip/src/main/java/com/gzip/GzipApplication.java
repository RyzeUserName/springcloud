package com.gzip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class GzipApplication {

    public static void main(String[] args) {
        SpringApplication.run(GzipApplication.class, args);
        //http://localhost:8011/search/repositories?str=spring-cloud-dubbo
    }

}
