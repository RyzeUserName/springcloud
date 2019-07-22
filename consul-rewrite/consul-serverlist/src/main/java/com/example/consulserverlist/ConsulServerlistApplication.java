package com.example.consulserverlist;

import com.ecwid.consul.v1.ConsulClient;
import com.example.consulserverlist.config.MyConsulServerList;
import com.example.consulserverlist.service.HelloService3;
import com.example.consulserverlist.service.HelloService4;
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

/**
 * 自定义的  ServerList 并不会达到预期的效果
 * ConsulServerList并不是在启动时初始化 而是在服务调用时 通过ribbon 初始化
 * feign 通过serviceId 去Ribbon获取服务端配置 这个过程就是初始化 consulServerList
 * so 覆盖的话  要么 重写 consul-discovery 要么重写 ribbon
 *
 */
@SpringBootApplication
@RestController
@EnableFeignClients
public class ConsulServerlistApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsulServerlistApplication.class, args);
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
