package com.lft.consumer.service;

import com.lft.consumer.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "provider")
public interface UserService {

    @GetMapping("/user/add")
    String add(User user);

    @PostMapping("/user/update")
    String update(@RequestBody User user);
}
