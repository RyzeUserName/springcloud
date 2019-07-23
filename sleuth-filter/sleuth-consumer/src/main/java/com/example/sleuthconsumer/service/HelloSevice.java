package com.example.sleuthconsumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "sleuth-provider", url = "localhost:8082")
public interface HelloSevice {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    String hello(@RequestParam String name);

}
