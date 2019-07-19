package com.example.consuloverrideconsumer;

import com.ecwid.consul.v1.ConsulClient;
import com.example.consuloverrideconsumer.config.MyConsulDiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class ConsulOverrideConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsulOverrideConsumerApplication.class, args);
    }

    @Autowired
    private DiscoveryClient discoveryClient;
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public DiscoveryClient consulDiscoveryClient(ConsulClient discoveryProperties,ConsulDiscoveryProperties consulClient) {
        return new MyConsulDiscoveryClient(discoveryProperties, consulClient);
    }
    @GetMapping("/getServer")
    public List<ServiceInstance> getServer(String serviceId) {
        return discoveryClient.getInstances(serviceId);
    }

}
