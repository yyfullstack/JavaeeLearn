package com.login.web.filter;


import javax.servlet.*;
import java.io.IOException;

/**
 * Created by yong on 2016/10/31 0031.
 * 过滤器处理表单传到servlet的乱码问题
 */
public class CharacterEncodingFilter implements Filter {

    private String encoding = null;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.encoding = filterConfig.getInitParameter("encoding");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(encoding);
        //servletResponse.setCharacterEncoding(encoding);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {

    }
}
