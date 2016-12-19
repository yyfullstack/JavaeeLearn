package com.smart4j.chapter2.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yong on 2016/11/21 0021.
 * 此过滤器用来解决解决get、post请求方式下的中文乱码问题
 */

@WebFilter(filterName="encodingFilter",urlPatterns="/*")
public class CharacterEncodingFilter implements Filter {

    private FilterConfig filterConfig = null;

    private String defaultCharset = "utf-8";


    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        //得到在web.xml中配置的字符编码
        String charset = filterConfig.getInitParameter("charset");
        if (charset == null) {
            charset = defaultCharset;
        }

        req.setCharacterEncoding(charset);
        resp.setCharacterEncoding(charset);
        resp.setContentType("text/html;charset=" + charset);

        MyCharacterEncodingRequest requestWrapper = new MyCharacterEncodingRequest(req);
        filterChain.doFilter(requestWrapper, resp);
    }

    public void destroy() {

    }


    /**
     * @ClassName: MyCharacterEncodingRequest
     * @Description: Servlet API中提供了一个request对象的Decorator设计模式的默认实现类HttpServletRequestWrapper,
     * (HttpServletRequestWrapper类实现了request接口中的所有方法，但这些方法的内部实现都是仅仅调用了一下所包装的的 request对象的对应方法)
     * 以避免用户在对request对象进行增强时需要实现request接口中的所有方法。
     * 所以当需要增强request对象时，只需要写一个类继承HttpServletRequestWrapper类，然后在重写需要增强的方法即可
     * @author: 孤傲苍狼
     * @date: 2014-9-2 下午10:42:57
     * 1.实现与被增强对象相同的接口
     * 2、定义一个变量记住被增强对象
     * 3、定义一个构造函数，接收被增强对象
     * 4、覆盖需要增强的方法
     * 5、对于不想增强的方法，直接调用被增强对象（目标对象）的方法
     */
    class MyCharacterEncodingRequest extends HttpServletRequestWrapper {
        //定义一个变量记住被增强对象(request对象是需要被增强的对象)
        private HttpServletRequest request;

        //定义一个构造函数，接收被增强对象
        public MyCharacterEncodingRequest(HttpServletRequest request) {
            super(request);
            this.request = request;
        }

        //覆盖需要增强的getParameter方法
        public String getParameter(String name) {
            //获取参数的值
            String value = this.request.getParameter(name);
            if (value == null) {
                return null;
            }
            try {
                if (!this.request.getMethod().equalsIgnoreCase("get")) {
                    return value;
                } else {
                    //new String(userName.getBytes("ISO8859-1"), "utf-8")
                    value = new String(value.getBytes("ISO8859-1"), this.request.getCharacterEncoding());
                    return value;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}