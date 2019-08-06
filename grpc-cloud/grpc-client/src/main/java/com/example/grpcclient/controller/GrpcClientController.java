package com.example.grpcclient.controller;

import com.example.grpcclient.service.GrpcServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * emil:miles02@613.com
 * Created by forezp on 2018/8/11.
 */
@RestController
public class GrpcClientController {

    @Autowired
    private GrpcServerService grpcClientService;

    @RequestMapping("/")
    public String printMessage(@RequestParam(defaultValue = "Spring Cloud") String name) {
        return grpcClientService.sendMessage(name);
    }
}