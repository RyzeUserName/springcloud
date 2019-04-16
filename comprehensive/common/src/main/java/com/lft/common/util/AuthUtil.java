package com.lft.common.util;

import com.lft.common.vo.User;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;

/**
 * 认证通用信息
 * @author Ryze
 * @date 2019/4/16 21:37
 */
public class AuthUtil {
    public static boolean authUser(User user, HttpServletResponse respone) throws Exception {
        if (StringUtils.isEmpty(user.getUserId())) {
            return false;
        } else {
            return true;
        }
    }
}
