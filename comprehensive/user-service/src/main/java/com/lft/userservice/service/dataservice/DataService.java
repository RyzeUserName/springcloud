package com.lft.userservice.service.dataservice;

import com.lft.userservice.service.fallback.UserClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "data-service", fallback = UserClientFallback.class)
public interface DataService {
    @RequestMapping(value = "/getDefaultUser", method = RequestMethod.GET)
    String getDefaultUser();

    @RequestMapping(value = "/getContextUserId", method = RequestMethod.GET)
    String getContextUserId();
}
