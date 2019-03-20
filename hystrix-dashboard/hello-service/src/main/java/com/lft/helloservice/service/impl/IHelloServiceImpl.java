package com.lft.helloservice.service.impl;

import com.lft.helloservice.service.IHelloService;
import com.lft.helloservice.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述
 * @author Ryze
 * @date 2019/3/20 20:39
 */
@Service
public class IHelloServiceImpl implements IHelloService {
    @Autowired
    ProviderService providerService;
    @Override
    public List<String> getProviderData() {
        return providerService.getProviderData();
    }
}
