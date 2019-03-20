package com.lft.client.service;

import com.lft.client.service.impl.UserServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@FeignClient(value = "client-provider",fallback = UserServiceFallback.class)
public interface IUserService {
    @RequestMapping(value = "/getUser",method = RequestMethod.GET)
    String getUser(String username) throws Exception;
}
