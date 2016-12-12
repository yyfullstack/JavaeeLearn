package com.yckjsoft.javaee.filter.demo;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yong on 2016/11/21 0021.
 * 控制缓存的filter
 */
public class CacheFilter implements Filter {

    private FilterConfig filterConfig;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        //1.获取用户想访问的资源
        String uri = req.getRequestURI();
        //2.得到用户想访问的资源的后缀名
        String ext = uri.substring(uri.lastIndexOf(".") + 1);

        //得到资源需要缓存的时间
        String time = filterConfig.getInitParameter(ext);
        if (time != null) {
            long t = Long.parseLong(time) * 3600 * 1000;
            //设置缓存
            resp.setDateHeader("expires",System.currentTimeMillis() + t);
        }

        filterChain.doFilter(req, resp);
    }

    public void destroy() {

    }


}