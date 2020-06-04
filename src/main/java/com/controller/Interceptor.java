package com.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 */
public class Interceptor implements HandlerInterceptor {
    static Logger log = LoggerFactory.getLogger(Interceptor.class);

    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object object, Exception e)
            throws Exception {

        System.out.println("afterCompletion");
        return;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse arg1,
                           Object arg2, ModelAndView arg3) throws Exception {

        System.out.println("postHandle");
        return;
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object arg2) throws Exception {
        System.out.println("preHandle");
        HttpServletRequest servletRequest=(HttpServletRequest)request;
        HttpServletResponse servletResponse=(HttpServletResponse)response;

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        System.out.println("路径:"+request.getRequestURI());
        return true;
    }
}
