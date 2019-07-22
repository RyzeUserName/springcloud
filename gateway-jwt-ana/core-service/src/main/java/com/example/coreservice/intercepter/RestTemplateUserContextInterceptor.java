package com.example.coreservice.intercepter;

import com.example.coreservice.vo.User;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 *  RestTemplate 请求的拦截器
 * @author Ryze
 * @date 2019-07-22 15:29
 */
public class RestTemplateUserContextInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
        throws IOException {
        User user = UserContextHolder.currentUser();
        request.getHeaders().add("x-user-id", user.getUserId());
        request.getHeaders().add("x-user-name", user.getUserName());
        request.getHeaders().add("x-user-serviceName", request.getURI().getHost());
        return execution.execute(request, body);
    }
}
