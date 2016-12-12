package com.yckjsoft.javaee.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by yong on 2016/11/21 0021.
 * filter的三种典型应用：
 * 1、可以在filter中根据条件决定是否调用chain.doFilter(request, response)方法，
 * 即是否让目标资源执行
 * 2、在让目标资源执行之前，可以对request\response作预处理，再让目标资源执行
 * 3、在目标资源执行之后，可以捕获目标资源的执行结果，从而实现一些特殊的功能
 */
public class FilterDemo1 implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("utf-8");
        servletResponse.setCharacterEncoding("utf-8");
        servletResponse.setContentType("text/html;charset=utf-8");
        System.out.println("FilterDemo1执行前--------");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("FilterDemo1执行后--------");
    }

    public void destroy() {
        System.out.println("过滤器销毁");
    }
}
