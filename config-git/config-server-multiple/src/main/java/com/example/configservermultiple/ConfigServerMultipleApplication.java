package com.example.configservermultiple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerMultipleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerMultipleApplication.class, args);
    }

}
