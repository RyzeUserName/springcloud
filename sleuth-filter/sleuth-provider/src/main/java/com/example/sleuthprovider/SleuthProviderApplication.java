package com.example.sleuthprovider;

import brave.propagation.ExtraFieldPropagation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SleuthProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SleuthProviderApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello(String name) {
        return "hello " + name + "----" + ExtraFieldPropagation.get("SessionId");
    }

}
