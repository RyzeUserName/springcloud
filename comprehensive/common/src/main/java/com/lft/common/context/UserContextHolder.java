package com.lft.common.context;

import com.lft.common.vo.User;

/**
 * 用户上下文
 * @author Ryze
 * @date 2019/4/16 21:52
 */
public class UserContextHolder {
    public static ThreadLocal<User> context = new ThreadLocal<User>();

    public static User currentUser() {
        return context.get();
    }

    public static void set(User user) {
        context.set(user);
    }

    public static void shutdown() {
        context.remove();
    }
}
