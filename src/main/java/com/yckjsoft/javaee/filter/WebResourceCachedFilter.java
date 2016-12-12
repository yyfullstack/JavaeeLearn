package com.yckjsoft.javaee.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yong on 2016/11/21 0021.
 * Web资源缓存过滤器
 */
public class WebResourceCachedFilter implements Filter {
    // 缓存Web资源的Map容器
    private Map<String, byte[]> map = new HashMap<String, byte[]>();

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        //1.得到用户请求的uri
        String uri = req.getRequestURI();
        //2.看缓存中有没有uri对应的数据
        byte[] bytes = map.get(uri);
        //3.如果缓存中有，直接拿缓存的数据打给浏览器，程序返回
        if (bytes != null) {
            //根据字节数组和指定的字符编码构建字符串
            String webResourceHtmlStr = new String(bytes, resp.getCharacterEncoding());
            System.out.println(webResourceHtmlStr);
            resp.getOutputStream().write(bytes);
            return;
        }
        //4.如果缓存没有，让目标资源执行，并捕获目标资源的输出
        BufferResponse responseWrapper = new BufferResponse(resp);
        filterChain.doFilter(req, responseWrapper);
        //获取缓冲流中的内容的字节数组
        byte[] out = responseWrapper.getBuffer();
        //5.把资源的数据以用户请求的uri为关键字保存到缓存中
        map.put(uri, out);
        //6.把数据打给浏览器
        resp.getOutputStream().write(out);
    }

    public void destroy() {

    }


    class BufferResponse extends HttpServletResponseWrapper {
        //捕获输出的缓存
        private ByteArrayOutputStream bout = new ByteArrayOutputStream();

        private PrintWriter pw;

        private HttpServletResponse response;

        public BufferResponse(HttpServletResponse response) {
            super(response);
            this.response = response;
        }


        //增强的方法
        /**
         * 源方法：
         * public ServletOutputStream getOutputStream() throws IOException {
         * return this.response.getOutputStream();
         * }
         *
         * @return
         * @throws IOException
         */
        @Override
        public ServletOutputStream getOutputStream() throws IOException {
            return new MyServletOutputStream(bout);
        }

        /**
         * 源方法：
         * public PrintWriter getWriter() throws IOException {
         * return this.response.getWriter();
         * }
         *
         * @return
         * @throws IOException
         */
        @Override
        public PrintWriter getWriter() throws IOException {
            pw = new PrintWriter(new OutputStreamWriter(bout, this.response.getCharacterEncoding()));
            return pw;
        }

        /**
         * 缓存的字节数组
         * @return
         */
        public byte[] getBuffer() {
            try {
                if (pw != null) {
                    pw.close();
                }
                return bout.toByteArray();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    class MyServletOutputStream extends ServletOutputStream {
        private ByteArrayOutputStream bout;

        //接收数据写到哪里
        public MyServletOutputStream(ByteArrayOutputStream bout) {
            this.bout = bout;
        }

        //增强的方法
        public void write(int b) throws IOException {
            this.bout.write(b);
        }
    }

}