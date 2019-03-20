package com.lft.loadbalance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 描述
 * @author Ryze
 * @date 2019-03-18 14:49
 */
@RestController
public class TestController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient lbClient;
    @GetMapping("/add")
    public String add(Integer a, Integer b) {
        String result = restTemplate.getForObject("http://client/test/add?a=" + a + "&b=" + b, String.class);
        System.out.println(result);
        return result;
    }
    @GetMapping("/add1")
    public void add1(Integer a, Integer b) {
        ServiceInstance instance = this.lbClient.choose("client");
        System.out.println(instance.getHost()+":"+instance.getPort());
    }
    @GetMapping("/add2")
    public void add2(Integer a, Integer b) {
        ServiceInstance instance = this.lbClient.choose("client");
        System.out.println(instance.getHost()+":"+instance.getPort());
    }
}
