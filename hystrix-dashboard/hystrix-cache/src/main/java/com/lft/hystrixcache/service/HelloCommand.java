package com.lft.hystrixcache.service;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import org.springframework.web.client.RestTemplate;

/**
 * 用类开启缓存 需要覆盖 getCacheKey 方法 保证同一个请求返回一样的键值
 * 清除缓存 可以直接调用HystrixRequestCache 类的clean 方法
 * @author Ryze
 * @date 2019-03-21 18:01
 */
public class HelloCommand extends HystrixCommand<String> {
    private RestTemplate restTemplate;
    private Integer id ;

    @Override
    protected String run() throws Exception {
        String forObject = restTemplate.getForObject("http://provider-service/getUser/{1}", String.class, id);
        System.out.println(forObject);
        return forObject;
    }

    @Override
    protected String getCacheKey() {
        return String.valueOf(id);
    }

    @Override
    protected String getFallbackMethodName() {
        return "fallback ---------------";
    }

    public static void cleanCache(Long id) {
        HystrixRequestCache.getInstance(
            HystrixCommandKey.Factory.asKey("springCloudCacheGroup"),
            HystrixConcurrencyStrategyDefault.getInstance()
        ).clear(String.valueOf(id));
    }

    public HelloCommand(RestTemplate restTemplate, Integer id) {
        super(HystrixCommandGroupKey.Factory.asKey("springCloudCacheGroup"));
        this.id = id;
        this.restTemplate = restTemplate;
    }
}
