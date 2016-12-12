package com.yckjsoft.javaee.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yong on 2016/11/21 0021.
 * 这个过滤器是用来解决中文乱码，转义内容中的html标签，过滤内容中的敏感字符的
 * 我们如果将上述的CharacterEncodingFilter、HtmlFilter、DirtyFilter这三个过滤器联合起来使用，
 * 那么就相当于是把request对象包装了3次，request对象的getParameter方法经过3次重写，使得getParameter方法的功能大大增强，
 * 可以同时解决中文乱码，html标签转义，敏感字符过滤这些需求。
 * 在实际开发中完全可以将上述的三个过滤器合并成一个，让合并后的过滤器具有解决中文乱码，html标签转义，敏感字符过滤这些功能
 */
public class AdvanceFilter implements Filter {

    private FilterConfig config = null;

    private String defaultCharset = "UTF-8";

    public void init(FilterConfig filterConfig) throws ServletException {
        //得到过滤器的初始化配置信息
        this.config = filterConfig;
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        //得到在web.xml中配置的字符编码
        String charset = config.getInitParameter("charset");
        if (charset == null) {
            charset = defaultCharset;
        }
        req.setCharacterEncoding(charset);
        resp.setCharacterEncoding(charset);
        resp.setContentType("text/html;charset=" + charset);


        AdvanceRequest requestWrapper = new AdvanceRequest(req);
        filterChain.doFilter(requestWrapper, resp);
    }

    public void destroy() {

    }

    private List<String> getDirtyWords() {
        List<String> dirtyWords = new ArrayList<String>();
        String dirtyWordPath = config.getInitParameter("DirtyWord");
        InputStream inputStream = config.getServletContext().getResourceAsStream(dirtyWordPath);
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line;
            //如果 line为空说明读完了
            while ((line = reader.readLine()) != null) {
                dirtyWords.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dirtyWords;
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


    class AdvanceRequest extends HttpServletRequestWrapper {

        private List<String> dirtyWords = getDirtyWords();

        private HttpServletRequest request;

        public AdvanceRequest(HttpServletRequest request) {
            super(request);
            this.request = request;
        }

        //覆盖需要增强的getParameter方法
        public String getParameter(String name) {
            String value = this.request.getParameter(name);
            if (value == null) {
                return null;
            }
            try {
                if (!this.request.getMethod().equalsIgnoreCase("get")) {
                    value = filter(value);
                } else {
                    value = new String(value.getBytes("ISO8859-1"), this.request.getCharacterEncoding());
                    value = filter(value);
                }

                for (String word : dirtyWords) {
                    if (value.contains(word)) {
                        System.out.println("内容中包含敏感词：" + word + "，将被替换成****");
                        //替换敏感字符
                        value = value.replace(word, "****");
                    }
                }
                return value;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
