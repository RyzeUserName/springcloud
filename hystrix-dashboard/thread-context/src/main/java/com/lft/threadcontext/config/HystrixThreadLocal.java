package com.lft.threadcontext.config;

/**
 * 描述
 * @author Ryze
 * @date 2019/3/21 23:02
 */
public class HystrixThreadLocal {
    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();
}
