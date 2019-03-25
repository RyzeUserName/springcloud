package com.example.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletResponse;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.POST_TYPE;

/**
 * post个实现
 * @author Ryze
 * @date 2019-03-25 9:29
 */
public class PostPreFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("post filter");
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletResponse response = currentContext.getResponse();
        // 解决乱码
        response.setContentType("text/html;charset=UTF-8");
        String responseBody = currentContext.getResponseBody();
        if (responseBody != null) {
            currentContext.setResponseStatusCode(500);
            currentContext.setResponseBody(responseBody);
        }
        return null;
    }
}
