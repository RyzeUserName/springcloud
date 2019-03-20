package com.lft.eureka.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EurekaClientConfigBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述
 * @author Ryze
 * @date 2019-03-08 17:17
 */
@RestController
@RequestMapping("/query")
public class QueryController {

    @Autowired
    private EurekaClientConfigBean eurekaClientConfigBean;

    @GetMapping("/eureka-server")
    public Object getEurekaUrl() {
        return eurekaClientConfigBean.getServiceUrl();
    }
}
