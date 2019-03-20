package com.lft.hystrixexception.service;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * 正常
 * @author Ryze
 * @date 2019/3/20 22:08
 */
public class ProviderServiceCommand extends HystrixCommand<String> {

    private final String name;

    public ProviderServiceCommand(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("GroupSC"));
        this.name = name;
    }

    @Override
    protected String run() {
        return "Spring Cloud";
    }

    @Override
    protected String getFallback() {
        return "Failure Spring Cloud";
    }

}
