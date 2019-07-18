package com.example.consulconsumerribbon;

import com.example.consulconsumerribbon.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@EnableFeignClients
public class ConsulConsumerRibbonApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsulConsumerRibbonApplication.class, args);
    }

    @Autowired
    private HelloService helloService;
    @Autowired
    private RestTemplate restTemplate;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/hello1")
    public String hello1(String name) {
        return helloService.hello(name);
    }

    @GetMapping("/hello2")
    public String hello2(String name) {
        return restTemplate.getForObject("http://consul-provider-tag/hello?name=" + name, String.class);
    }
}
