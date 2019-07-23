package com.example.sleuthprovider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SleuthProviderApplication {
    private static final Logger logger = LoggerFactory.getLogger(SleuthProviderApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SleuthProviderApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello(String name) {
        logger.info("服务器 收到信息 参数：{}", name);
        String result = "hello " + name;
        logger.info("服务器 返回信息 结果：{}", result);
        return result;
    }

}
