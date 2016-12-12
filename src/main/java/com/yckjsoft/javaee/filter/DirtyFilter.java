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
 * 敏感词过滤器
 */
public class DirtyFilter implements Filter {

    private FilterConfig config = null;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;


        MyDirtyRequest requestWrapper = new MyDirtyRequest(req);
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

    class MyDirtyRequest extends HttpServletRequestWrapper {

        private List<String> dirtyWords = getDirtyWords();

        private HttpServletRequest request;

        public MyDirtyRequest(HttpServletRequest request) {
            super(request);
            this.request = request;
        }

        public String getParameter(String name) {
            String value = this.request.getParameter(name);
            if (value == null) {
                return null;
            }
            for (String word : dirtyWords) {
                if (value.contains(word)) {
                    System.out.println("内容中包含敏感词：" + word + "，将被替换成****");
                    //替换敏感字符
                    value = value.replace(word, "****");
                }
            }
            return value;
        }
    }
}
