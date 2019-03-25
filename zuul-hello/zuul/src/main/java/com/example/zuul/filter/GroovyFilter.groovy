package com.example.zuul.filter

import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import com.netflix.zuul.exception.ZuulException

import javax.servlet.http.HttpServletRequest

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE

class GroovyFilter extends ZuulFilter {
    @Override
    String filterType() {
        return PRE_TYPE
    }

    @Override
    int filterOrder() {
        return 10
    }

    @Override
    boolean shouldFilter() {
        return true
    }

    @Override
    Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext()
        HttpServletRequest request = currentContext.getRequest()
        def iterator = request.getHeaderNames().iterator()
        while (iterator.hasNext()) {
            String name = (String) iterator.next()
            String value = request.getHeader(name)
            println("header: name-->" + name + "value---->" + value)
        }
        println("---------groovy filter---------修改了")
        return null
    }
}
