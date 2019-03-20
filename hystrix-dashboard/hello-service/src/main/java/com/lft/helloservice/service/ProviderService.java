package com.lft.helloservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "provider-service")
public interface ProviderService {

    @RequestMapping(value = "/getDashboard", method = RequestMethod.GET)
     List<String> getProviderData();
}
