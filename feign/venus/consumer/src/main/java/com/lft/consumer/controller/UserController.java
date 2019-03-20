package com.lft.consumer.controller;

import com.lft.providerclient.entity.User;
import com.lft.providerclient.service.UserService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private UserService userService;

    /**
     * 用于演示Feign的Get请求多参数传递
     * @param user
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@RequestBody @ApiParam(name = "用户", value = "传入json格式", required = true) User user) {
        return userService.add(user);
    }

    /**
     * 用于演示Feign的Post请求多参数传递
     * @param user
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateUser(@RequestBody @ApiParam(name = "用户", value = "传入json格式", required = true) User user) {
        return userService.update(user);

    }
}