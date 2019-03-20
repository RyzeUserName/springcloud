package com.lft.helloservice.controller;

import com.lft.helloservice.service.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 描述
 * @author Ryze
 * @date 2019/3/20 20:37
 */
@RestController
public class HelloController {

    @Autowired
    private IHelloService userService;

    @RequestMapping(value = "/helloService", method = RequestMethod.GET)
    public String getHelloServiceData() {
        return "hello Service";
    }


    @GetMapping("/getProviderData")
    public List<String> getProviderData() {
        return userService.getProviderData();
    }
}
