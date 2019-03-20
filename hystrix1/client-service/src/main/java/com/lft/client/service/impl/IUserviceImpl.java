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
public class IUserviceImpl implements IUserService {

    @Override
    @HystrixCommand(fallbackMethod = "defaultUser")
    public String getUser(String username) throws Exception {
        if (username.equals("spring")) {
            return "This is real user";
        } else {
            throw new Exception();
        }
    }
    /**
     * 出错则调用该方法返回友好错误
     * @param username
     * @return
     */
    public String defaultUser(String username) {
        return "The user does not exist in this system";
    }
}
