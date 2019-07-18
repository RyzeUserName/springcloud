package com.example.consulprovidertag1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ConsulProviderTag1Application {

    public static void main(String[] args) {
        SpringApplication.run(ConsulProviderTag1Application.class, args);
    }
    @GetMapping("/hello")
    public String hello(String name) {
        return "hello " + name + " from tag1";
    }

}
