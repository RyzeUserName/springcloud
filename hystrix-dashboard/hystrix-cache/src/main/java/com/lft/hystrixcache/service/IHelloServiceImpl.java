package com.lft.hystrixcache.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 描述
 * @author Ryze
 * @date 2019-03-21 18:44
 */
@Service
public class IHelloServiceImpl implements IHelloService {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    @CacheResult
    @HystrixCommand
    public String hello(Integer id) {
        String forObject = restTemplate.getForObject("http://provider-service/getUser/{1}", String.class, id);
        System.out.println(forObject);
        return forObject;
    }

    @Override
    @CacheResult
    @HystrixCommand(commandKey = "getUser3")
    public String hello1(Integer id) {
        String forObject = restTemplate.getForObject("http://provider-service/getUser/{1}", String.class, id);
        System.out.println(forObject);
        return forObject;
    }

    @Override
    @CacheRemove(commandKey = "getUser3")
    @HystrixCommand()
    public String update(Integer id) {
        System.out.println("删除缓存 ");
        return "删除";
    }
}
