package com.example.consulregister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ConsulRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsulRegisterApplication.class, args);
    }

    /**
     * 自定义的健康监测接口
     * @return
     */
    @GetMapping("/health")
    public String health() {
        return "SUCCESS";
    }

}
