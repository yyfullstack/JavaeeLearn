package com.yckjsoft.javaee.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yong on 2016/11/21 0021.
 * html转义过滤器
 */
public class HtmlFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;


        MyHtmlRequest requestWrapper = new MyHtmlRequest(req);
        filterChain.doFilter(requestWrapper, resp);
    }

    public void destroy() {

    }


    class MyHtmlRequest extends HttpServletRequestWrapper {

        private HttpServletRequest request;

        public MyHtmlRequest(HttpServletRequest request) {
            super(request);
            this.request = request;
        }
        //覆盖需要增强的getParameter方法
        public String getParameter(String name) {
            String value = this.request.getParameter(name);
            if (value == null) {
                return null;
            }
            //调用filter转义value中的html标签
            return filter(value);
        }

        public String filter(String message) {

            if (message == null) {
                return (null);
            }
            char content[] = new char[message.length()];
            message.getChars(0, message.length(), content, 0);
            StringBuffer result = new StringBuffer(content.length + 50);
            for (int i = 0; i < content.length; i++) {
                switch (content[i]) {
                    case '<':
                        result.append("&lt;");
                        break;
                    case '>':
                        result.append("&gt;");
                        break;
                    case '&':
                        result.append("&amp;");
                        break;
                    case '"':
                        result.append("&quot;");
                        break;
                    default:
                        result.append(content[i]);
                }
            }
            return (result.toString());
        }
    }
}