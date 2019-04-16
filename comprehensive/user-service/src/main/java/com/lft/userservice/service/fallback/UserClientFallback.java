package com.lft.userservice.service.fallback;

import com.lft.userservice.service.dataservice.DataService;
import org.springframework.stereotype.Component;

/**
 * 描述
 * @author Ryze
 * @date 2019/4/16 22:35
 */
@Component
public class UserClientFallback implements DataService {
    @Override
    public String getDefaultUser() {
        return "get getDefaultUser failed";
    }

    @Override
    public String getContextUserId() {
        return "get getContextUserId failed";
    }
}
