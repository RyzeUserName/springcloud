package com.lft.threadcontext.service;

import com.lft.threadcontext.config.HystrixThreadLocal;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * 描述
 * @author Ryze
 * @date 2019/3/21 23:10
 */
@Service
public class IUserserviceImpl implements IUserservice {
    @Autowired
    RestTemplate restTemplate;

    @Override
    @HystrixCommand
    public String getUser(Integer id) {
        System.out.println("当前线程 " + Thread.currentThread().getId());
        System.out.println("threadLocal --> " + HystrixThreadLocal.threadLocal.get());
        System.out.println("放入RequestContextHolder --> " + RequestContextHolder.currentRequestAttributes().getAttribute("userId", RequestAttributes.SCOPE_REQUEST));
        String forObject = restTemplate.getForObject("http://provider-service/getUser/{1}", String.class, id);
        System.out.println(forObject);
        return forObject;
    }
}
