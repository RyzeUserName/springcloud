package com.example.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * 第二个实现
 * @author Ryze
 * @date 2019-03-25 9:29
 */
public class SecondPreFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("第二个 filter");
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String a = request.getParameter("a");
        if (a == null) {
            //禁止路由
            currentContext.setSendZuulResponse(false);
            //设置 body
            currentContext.setResponseBody("{\"status\":500,\"message\":\"缺少参数a\"}");
            //设置logic-is-success 保存于上下文中 作为 下游同类型filter的执行开关
            currentContext.set("logic-is-success", false);
            return null;
        }
        currentContext.set("logic-is-success", true);
        return null;
    }
}
