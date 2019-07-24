package com.example.servicea.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 描述
 * @author Ryze
 * @date 2019-07-24 17:09
 */
@FeignClient("service-b")
public interface ServiceB {

    @GetMapping("/getInfo")
    String getInfo(@RequestParam("serviceName") String serviceName);
}
