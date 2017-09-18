package com.scx.subscription.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class LoginInterceptor implements HandlerInterceptor {

    private List<String> excludeUrls;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String requestPath = request.getRequestURI() + "?" + request.getQueryString();
        if (requestPath.contains("&")) {// 去掉其他参数
            requestPath = requestPath.substring(0, requestPath.indexOf("&"));
        }
        requestPath = requestPath.substring(request.getContextPath().length() + 1);// 去掉项目路径
        requestPath = requestPath.split("\\?")[0];
        if (excludeUrls.contains(requestPath)) {
            return true;
        }
        if (request.getSession().getAttribute("user") == null) {
            request.getRequestDispatcher("/login/prompt.do").forward(request, response);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    public List<String> getExcludeUrls() {
        return excludeUrls;
    }

    public void setExcludeUrls(List<String> excludeUrls) {
        this.excludeUrls = excludeUrls;
    }
}
