package com.xian.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 过滤器demo
 *
 * @author Xian
 * @Date 2021/10/17 18:01
 */
//@WebFilter("/*")
public class filterDemo02 implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        //对request对象请求消息进行增强
        System.out.println("filterDemo02 is using....");
        //放行
        chain.doFilter(req, resp);
        //对response对象响应消息进行增强
        System.out.println("filterDemo02 is coming...");
    }

    @Override
    public void destroy() {

    }
}
