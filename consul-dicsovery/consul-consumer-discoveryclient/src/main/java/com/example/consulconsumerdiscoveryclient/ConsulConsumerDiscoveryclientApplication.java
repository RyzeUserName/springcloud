package com.example.consulconsumerdiscoveryclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class ConsulConsumerDiscoveryclientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsulConsumerDiscoveryclientApplication.class, args);
    }

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/getServer")
    public List<ServiceInstance> getServer(String serviceId) {
        return discoveryClient.getInstances(serviceId);
    }

}
