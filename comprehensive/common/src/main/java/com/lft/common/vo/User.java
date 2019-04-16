package com.lft.common.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户
 * @author Ryze
 * @date 2019/4/16 21:34
 */
public class User implements Serializable {

    private static final long serialVersionUID = -4083327605430665846L;

    public final static String CONTEXT_KEY_USER_ID = "x-customs-user";

    /**
     * 用户ID
     */
    private String userId;

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public User() {

    }

    public User(Map<String, String> headers) {
        userId = headers.get(CONTEXT_KEY_USER_ID);
    }


    /**
     * 将user对象转换成为http对象头
     * @return http头键值对
     */
    public Map<String, String> toHttpHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put(CONTEXT_KEY_USER_ID, userId);
        return headers;
    }
}
