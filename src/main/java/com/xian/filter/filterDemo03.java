package com.xian.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 过滤器demo
 *
 * @author Xian
 * @Date 2021/10/17 18:54
 */
@WebFilter("/*")
public class filterDemo03 implements Filter {
    /**
     * 在服务器创建后调用init方法，只执行一次
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init....");
    }

    /**
     * 每一次请求被拦截资源时，会执行
     * @param req
     * @param resp
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        System.out.println("doFilter...");
        chain.doFilter(req, resp);
    }

    /**
     * 在服务器关闭后，Filter对象被销毁，如果服务器是被正常关闭，则会执行destroy方法，只执行一次
     */
    @Override
    public void destroy() {
        System.out.println("destroy....");
    }
}
