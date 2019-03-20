package com.lft.provider.controller;

import com.lft.provider.entity.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述
 * @author Ryze
 * @date 2019/3/17 7:23
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 用于演示Feign的Get请求多参数传递
     * @param user
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addUser(User user) {
        return "hello--" + user.getName() + "--add";
    }

    /**
     * 用于演示Feign的Post请求多参数传递
     * @param user
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateUser(@RequestBody User user) {
        return "hello--" + user.getName() + "--update";
    }
}