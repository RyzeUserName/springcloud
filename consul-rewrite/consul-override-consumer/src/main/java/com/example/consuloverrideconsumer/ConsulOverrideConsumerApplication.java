package com.example.consuloverrideconsumer;

import com.ecwid.consul.v1.ConsulClient;
import com.example.consuloverrideconsumer.config.MyConsulDiscoveryClient;
import com.example.consuloverrideconsumer.config.service.HelloService;
import com.netflix.loadbalancer.ServerList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.composite.CompositeDiscoveryClientAutoConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
@RestController
@EnableFeignClients
public class ConsulOverrideConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsulOverrideConsumerApplication.class, args);
    }

    @Autowired
    private DiscoveryClient discoveryClient;
    //CompositeDiscoveryClientAutoConfiguration 类写了 Primary 故不能重写
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public DiscoveryClient consulDiscoveryClient(ConsulClient discoveryProperties, ConsulDiscoveryProperties consulClient) {
        return new MyConsulDiscoveryClient(discoveryProperties, consulClient);
    }

    /**
     *  这个方法 不支持tag
     * @param serviceId
     * @return
     */
    @GetMapping("/getServer")
    public List<ServiceInstance> getServer(String serviceId) {
        return discoveryClient.getInstances(serviceId);
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
