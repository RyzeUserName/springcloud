package com.lft.hystrixexception.service;

import com.netflix.hystrix.exception.HystrixBadRequestException;
import feign.Response;
import feign.Util;

import java.io.IOException;

/**
 * feign 异常处理
 * @author Ryze
 * @date 2019/3/20 22:13
 */
public class FeignErrorDecoder implements feign.codec.ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            if (response.status() >= 400 && response.status() <= 499) {
                String error = Util.toString(response.body().asReader());
                return new HystrixBadRequestException(error);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return feign.FeignException.errorStatus(methodKey, response);
    }
}
