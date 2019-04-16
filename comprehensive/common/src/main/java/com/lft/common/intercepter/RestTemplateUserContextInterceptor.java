package com.lft.common.intercepter;

import com.lft.common.context.UserContextHolder;
import com.lft.common.vo.User;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.util.Map;

/**
 * RestTemplate 调用时 将上下文中属性传递下去
 * @author Ryze
 * @date 2019/4/16 21:47
 */
public class RestTemplateUserContextInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        User user = UserContextHolder.currentUser();
        Map<String, String> headers = user.toHttpHeaders();
        for (Map.Entry<String, String> header : headers.entrySet()) {
            httpRequest.getHeaders().add(header.getKey(), header.getValue());
        }
        // 调用
        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }
}
