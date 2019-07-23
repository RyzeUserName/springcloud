package com.example.sleuthconsumer.controller;

import com.example.sleuthconsumer.service.HelloSevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

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
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ExecutorService executorService;

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

    /**
     * RestTemplate
     * @param name
     * @return
     */
    @GetMapping("/hello2")
    public String hello2(String name) {
        logger.info("RestTemplate 的方式调用，参数：{}", name);
        String hello = restTemplate.getForObject("http://localhost:8082/hello?name=" + name, String.class);
        logger.info("RestTemplate 的方式返回，结果：{}", hello);
        return hello;
    }

    /**
     * 子线程
     * @param name
     * @return
     */
    @GetMapping("/hello3")
    public String hello3(String name) throws ExecutionException, InterruptedException {
        logger.info("子线程 的方式调用，参数：{}", name);
        Future<String> submit = (Future<String>) executorService.submit(() -> {
            logger.info("子线程 的方式调用，参数：{}", name);
            return helloSevice.hello(name);
        });
        String hello = submit.get();
        logger.info("子线程 的方式返回，结果：{}", hello);
        return hello;
    }
}
