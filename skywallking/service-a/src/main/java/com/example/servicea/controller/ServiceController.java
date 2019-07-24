package com.example.servicea.controller;

import com.example.servicea.service.ServiceB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述
 * @author Ryze
 * @date 2019-07-24 17:11
 */
@RestController
public class ServiceController {

    @Autowired
    private ServiceB serviceB;

    @GetMapping("/getInfo")
    public String getInfo(@RequestParam("serviceName") String serviceName) {
        return serviceB.getInfo(serviceName);
    }
}
