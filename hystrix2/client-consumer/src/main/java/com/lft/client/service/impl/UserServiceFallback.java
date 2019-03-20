package com.lft.client.service.impl;

import com.lft.client.service.IUserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Component;

/**
 * 描述
 * @author Ryze
 * @date 2019-03-20 14:16
 */
@Component
public class UserServiceFallback implements IUserService {

    @Override
    public String getUser(String username) throws Exception {
        return "The user does not exist in this system";
    }
}
