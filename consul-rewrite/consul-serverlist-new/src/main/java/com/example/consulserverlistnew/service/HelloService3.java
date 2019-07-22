package com.example.consulserverlistnew.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 描述
 * @author Ryze
 * @date 2019-07-18 20:05
 */
@FeignClient("consul-provider-tag-3")
public interface HelloService3 {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    String hello(@RequestParam("name") String name);
}
