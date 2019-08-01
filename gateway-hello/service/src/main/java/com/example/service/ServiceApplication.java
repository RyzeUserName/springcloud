package com.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
@RestController
public class ServiceApplication {
    private static final Logger logger = LoggerFactory.getLogger(ServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }

    @GetMapping("/test")
    public String test(HttpServletRequest request) {
        String q = request.getHeader("q");
        return q;
    }

    @GetMapping("/parameter")
    public String parameter(String parameter) {
        return parameter;
    }

    ConcurrentHashMap<String, AtomicInteger> stringAtomicIntegerConcurrentHashMap = new ConcurrentHashMap<>();

    @GetMapping("/retry")
    public String retry(String key, int count) {
        AtomicInteger atomicInteger = stringAtomicIntegerConcurrentHashMap.computeIfAbsent(key, s -> new AtomicInteger());
        int i = atomicInteger.incrementAndGet();
        logger.warn("重试次数： " + i);
        if (i < count) {
            throw new RuntimeException("处理失败，请重试");
        }
        stringAtomicIntegerConcurrentHashMap.clear();
        return "重试" + count + "次成功返回";
    }

    @GetMapping("/hystrix")
    public String hystrix(boolean isSleep) throws InterruptedException {
        logger.info("isSleep: " + isSleep);
        if (isSleep) {
            TimeUnit.MINUTES.sleep(10);
        }
        return "NO sleep";
    }

}
