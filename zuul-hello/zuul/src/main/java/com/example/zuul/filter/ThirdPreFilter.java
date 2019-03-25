package com.example.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * 第三个实现
 * @author Ryze
 * @date 2019-03-25 9:29
 */
public class ThirdPreFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 3;
    }

    @Override
    public boolean shouldFilter() {
        return (boolean)RequestContext.getCurrentContext().get("logic-is-success");
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("第三个 filter");
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String b = request.getParameter("b");
        if (b == null) {
            //设置 body
            currentContext.setResponseBody("{\"status\":500,\"message\":\"缺少参数b\"}");
            //禁止路由
            currentContext.setSendZuulResponse(false);
            //设置logic-is-success 保存于上下文中 作为 下游同类型filter的执行开关
            currentContext.set("logic-is-success", false);
            return null;
        }
        currentContext.set("logic-is-success", true);
        return null;
    }
}
