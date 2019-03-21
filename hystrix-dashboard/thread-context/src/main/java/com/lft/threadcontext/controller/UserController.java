package com.lft.threadcontext.controller;

import com.lft.threadcontext.config.HystrixThreadLocal;
import com.lft.threadcontext.service.IUserservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * 描述
 * @author Ryze
 * @date 2019/3/21 23:04
 */
@RestController
public class UserController {
    @Autowired
    IUserservice iUserservice;

    @GetMapping("/getUser/{id}")
    public String getUser(@PathVariable Integer id) {
        //放入 threadLocal
        HystrixThreadLocal.threadLocal.set("userId:" + id);
        //放入RequestContextHolder
        RequestContextHolder.currentRequestAttributes().setAttribute("userId", "userId:" + id, RequestAttributes.SCOPE_REQUEST);
        System.out.println("当前线程 " + Thread.currentThread().getId());
        System.out.println("threadLocal --> " + HystrixThreadLocal.threadLocal.get());
        System.out.println("放入RequestContextHolder --> " + RequestContextHolder.currentRequestAttributes().getAttribute("userId", RequestAttributes.SCOPE_REQUEST));

        return iUserservice.getUser(id);
    }
}
