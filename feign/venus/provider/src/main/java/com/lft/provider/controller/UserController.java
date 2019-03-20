package com.lft.provider.controller;

import com.lft.providerclient.entity.User;
import com.lft.providerclient.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述
 * @author Ryze
 * @date 2019/3/17 7:23
 */
@RestController
public class UserController implements UserService {

    /**
     * 用于演示Feign的Get请求多参数传递
     * @param user
     * @return
     */
    @Override
    public String add(User user) {
        return "hello--" + user.getName() + "--add";
    }

    /**
     * 用于演示Feign的Post请求多参数传递
     * @param user
     * @return
     */
    @Override
    public String update(@RequestBody User user) {
        return "hello--" + user.getName() + "--update";
    }
}