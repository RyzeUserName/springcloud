package com.lft.zuulserver.filter;

import com.alibaba.fastjson.JSONObject;
import com.lft.common.exception.BaseException;
import com.lft.common.exception.BaseExceptionBody;
import com.lft.common.exception.CommonError;
import com.lft.common.util.HttpConvertUtil;
import com.lft.common.vo.User;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 认证
 * @author Ryze
 * @date 2019/4/16 21:10
 */
public class AuthFilter extends ZuulFilter {
    private static final Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    @Override
    public String filterType() {
        return "pre";
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
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        Map<String, String> params = HttpConvertUtil.httpRequestToMap(request);
        String userId = params.get(User.CONTEXT_KEY_USER_ID);
        if (StringUtils.isEmpty(userId)) {
            try {
                BaseException BaseException = new BaseException(CommonError.AUTH_EMPTY_ERROR.getCode(), CommonError.AUTH_EMPTY_ERROR.getCodeEn(), CommonError.AUTH_EMPTY_ERROR.getMessage(), 1L);
                BaseExceptionBody errorBody = new BaseExceptionBody(BaseException);
                currentContext.setSendZuulResponse(false);
                currentContext.setResponseStatusCode(401);
                currentContext.setResponseBody(JSONObject.toJSON(errorBody).toString());
            } catch (Exception e) {
                logger.error("println message error", e);
            }
        } else {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                currentContext.addZuulRequestHeader(entry.getKey(), entry.getValue());
            }
        }
        return null;
    }
}
