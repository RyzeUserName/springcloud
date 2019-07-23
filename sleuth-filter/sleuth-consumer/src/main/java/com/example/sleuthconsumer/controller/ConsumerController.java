package com.example.sleuthconsumer.controller;

import com.example.sleuthconsumer.service.HelloSevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述
 * @author Ryze
 * @date 2019-07-23 14:01
 */
@RestController
public class ConsumerController {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerController.class);

    @Autowired
    private HelloSevice helloSevice;

    /**
     * feign
     * @param name
     * @return
     */
    @GetMapping("/hello1")
    public String hello1(String name) {
        logger.info("feign 的方式调用，参数：{}", name);
        String hello = helloSevice.hello(name);
        logger.info("feign 的方式返回，结果：{}", hello);
        return hello;
    }

}
