package com.example.alphaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "org.apache.servicecomb.saga")
public class AlphaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlphaServerApplication.class, args);
    }

}
