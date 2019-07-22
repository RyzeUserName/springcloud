package com.example.coreservice.intercepter;

import com.example.coreservice.vo.User;

/**
 * 线程 绑定的 用户信息
 * @author Ryze
 * @date 2019-07-22 15:30
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
