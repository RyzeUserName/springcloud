package com.lft.client.controller;

import com.lft.client.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述
 * @author Ryze
 * @date 2019-03-20 14:15
 */
@RestController
public class TestController {
    @Autowired
    IUserService iUserService;

    @GetMapping("/user")
    public String getUser(String username) throws Exception {
        return iUserService.getUser(username);
    }

}
