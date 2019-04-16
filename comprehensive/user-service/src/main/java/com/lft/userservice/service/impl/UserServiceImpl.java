package com.lft.userservice.service.impl;

import com.lft.userservice.service.IUserService;
import com.lft.userservice.service.dataservice.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 描述
 * @author Ryze
 * @date 2019/4/16 22:33
 */
@Component
public class UserServiceImpl implements IUserService {
    @Autowired
    private DataService dataService;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String getDefaultUser() {
        return dataService.getDefaultUser();
    }

    @Override
    public String getContextUserId() {
        return dataService.getContextUserId();
    }

    @Override
    public List<String> getProviderData() {
        List<String> result = restTemplate.getForObject("http://dataservice-service/getProviderData", List.class);
        return result;
    }
}
