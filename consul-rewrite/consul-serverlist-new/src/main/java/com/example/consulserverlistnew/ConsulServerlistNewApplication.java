package com.example.consulserverlistnew;

import com.ecwid.consul.v1.ConsulClient;
import com.example.consulserverlistnew.config.MyConsulServerList;
import com.example.consulserverlistnew.service.HelloService3;
import com.example.consulserverlistnew.service.HelloService4;
import com.netflix.client.config.DefaultClientConfigImpl;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.ServerList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableFeignClients
public class ConsulServerlistNewApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsulServerlistNewApplication.class, args);
    }
    @Autowired
    private HelloService3 helloService3;
    @Autowired
    private HelloService4 helloService4;

    @RequestMapping(value = "/hello3", method = RequestMethod.GET)
    String hello3(@RequestParam("name") String name) {
        return helloService3.hello(name);
    }

    @RequestMapping(value = "/hello4", method = RequestMethod.GET)
    String hello4(@RequestParam("name") String name) {
        return helloService4.hello(name);
    }

    @Bean
    public ServerList<?> ribbonServerList(ConsulClient consulClient, ConsulDiscoveryProperties consulDiscoveryProperties) {
        MyConsulServerList myConsulServerList = new MyConsulServerList(consulClient, consulDiscoveryProperties);
        IClientConfig config = new DefaultClientConfigImpl();
        myConsulServerList.initWithNiwsConfig(config);
        return myConsulServerList;
    }

}
