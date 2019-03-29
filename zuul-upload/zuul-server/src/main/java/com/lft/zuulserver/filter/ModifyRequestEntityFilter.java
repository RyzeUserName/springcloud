package com.lft.zuulserver.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * 给每个请求 加个参数
 * @author Ryze
 * @date 2019-03-29 18:39
 */
public class ModifyRequestEntityFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER + 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        Map<String, List<String>> requestQueryParams = currentContext.getRequestQueryParams();
        if (requestQueryParams == null) {
            requestQueryParams = new HashMap<>(16);
        }
        List<String> strings = new ArrayList<>();
        strings.add("oooooooooo");
        requestQueryParams.put("test", strings);
        currentContext.setRequestQueryParams(requestQueryParams);
        return null;
    }
}
