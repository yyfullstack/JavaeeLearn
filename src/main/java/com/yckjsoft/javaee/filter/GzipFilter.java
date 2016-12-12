package com.yckjsoft.javaee.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

/**
 * Created by yong on 2016/11/21 0021.
 * 压缩过滤器，将web应用中的文本都经过压缩后再输出到浏览器
 */
public class GzipFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        BufferResponse responseWrapper = new BufferResponse(resp);
        filterChain.doFilter(req, responseWrapper);
        //拿出缓存中的数据，压缩后再打给浏览器
        byte[] out = responseWrapper.getBuffer();
        System.out.println("原始大小：" + out.length);

        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        //压缩输出流中的数据
        GZIPOutputStream gout = new GZIPOutputStream(bout);
        gout.write(out);
        gout.close();

        byte[] gzip = bout.toByteArray();
        System.out.println("压缩后的大小：" + gzip.length);

        resp.setHeader("content-encoding", "gzip");
        resp.setContentLength(gzip.length);
        resp.getOutputStream().write(gzip);
    }

    public void destroy() {

    }


    class BufferResponse extends HttpServletResponseWrapper {

        private ByteArrayOutputStream bout = new ByteArrayOutputStream();
        private PrintWriter pw;
        private HttpServletResponse response;


        public BufferResponse(HttpServletResponse response) {
            super(response);
            this.response = response;
        }

        @Override
        public ServletOutputStream getOutputStream() throws IOException {
            return new MyServletOutputStream(bout);
        }

        @Override
        public PrintWriter getWriter() throws IOException {
            pw = new PrintWriter(new OutputStreamWriter(bout, this.response.getCharacterEncoding()));
            return pw;
        }

        public byte[] getBuffer() {
            try {
                if (pw != null) {
                    pw.close();
                }
                if (bout != null) {
                    bout.flush();
                    return bout.toByteArray();
                }
                return null;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    class MyServletOutputStream extends ServletOutputStream {
        private ByteArrayOutputStream bout;

        public MyServletOutputStream(ByteArrayOutputStream bout) {
            this.bout = bout;
        }

        public void write(int b) throws IOException {
            this.bout.write(b);
        }
    }

}