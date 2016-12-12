package com.yckjsoft.javaee.filter;

import javax.servlet.*;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by yong on 2016/11/21 0021.
 * 利用FilterConfig得到filter配置信息
 */
public class FilterDemo2 implements Filter {
    /**
     * 过滤器初始化
     * @param filterConfig
     * @throws ServletException
     */
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化");
        //得到过滤器的名字
        String filterName = filterConfig.getFilterName();
        //得到在web.xml文件中配置的初始化参数
        String initParam1 = filterConfig.getInitParameter("name");
        String initParam2 = filterConfig.getInitParameter("like");
        //返回过滤器的所有初始化参数的名字的枚举集合。
        Enumeration<String> initParamterNames = filterConfig.getInitParameterNames();
        System.out.println("filterName = " + filterName);
        System.out.println("initParam1 = " + initParam1);
        System.out.println("initParam2 = " + initParam2);

        while (initParamterNames.hasMoreElements()) {
            String paramName = initParamterNames.nextElement();
            System.out.println("paramName = " + paramName);
        }
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("FilterDemo2执行前--------");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("FilterDemo2执行后--------");
    }

    public void destroy() {
        System.out.println("过滤器销毁");
    }
}
