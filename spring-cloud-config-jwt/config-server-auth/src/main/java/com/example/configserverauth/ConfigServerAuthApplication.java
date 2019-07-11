package com.example.configserverauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerAuthApplication.class, args);
    }

}
