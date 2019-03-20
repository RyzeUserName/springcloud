package com.hello.controller;

import com.hello.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述
 * @author Ryze
 * @date 2019-03-15 16:55
 */
@RestController
public class HelloController {
    @Autowired
    private HelloService helloService;

    @GetMapping("/search/repositories")
    public String search(String str) {
        return helloService.search(str);
    }
}
