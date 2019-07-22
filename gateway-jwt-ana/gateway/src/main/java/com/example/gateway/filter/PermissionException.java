package com.example.gateway.filter;

/**
 * 描述
 * @author Ryze
 * @date 2019-07-22 15:09
 */
public class PermissionException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PermissionException(String msg) {
        super(msg);
    }

}
