package com.lft.hystrixcache.controller;

import com.lft.hystrixcache.service.HelloCommand;
import com.lft.hystrixcache.service.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 描述
 * @author Ryze
 * @date 2019-03-21 18:11
 */
@RestController
public class CacheController {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    IHelloService iHelloService;
    @GetMapping("/getUser1/{id}")
    public String getUser1(@PathVariable("id") Integer id) {
        HelloCommand helloCommand = new HelloCommand(restTemplate, id);
        helloCommand.execute();
        System.out.println("第一次是否来缓存：" + helloCommand.isResponseFromCache());
        HelloCommand helloCommand1 = new HelloCommand(restTemplate, id);
        helloCommand1.execute();
        System.out.println("第二次是否来缓存：" + helloCommand1.isResponseFromCache());
        return "success";
    }
    @GetMapping("/getUser2/{id}")
    public String getUser2(@PathVariable("id") Integer id) {
        iHelloService.hello(id);
        iHelloService.hello(id);
        return "success";
    }
    @GetMapping("/getUser3/{id}")
    public String getUser3(@PathVariable("id") Integer id) {
        iHelloService.hello1(id);
        iHelloService.hello1(id);
        iHelloService.update(id);
        iHelloService.hello1(id);
        iHelloService.hello1(id);
        return "success";
    }
}
