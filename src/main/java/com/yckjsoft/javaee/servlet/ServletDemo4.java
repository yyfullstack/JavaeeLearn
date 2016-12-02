package com.yckjsoft.javaee.servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yong on 2016/10/8 0008.
 * <p>
 * 在客户端缓存Servlet的输出
 * 对于不经常变化的数据，在servlet中可以为其设置合理的缓存时间值，以避免浏览器频繁向服务器发送请求，
 * 提升服务器的性能
 */
public class ServletDemo4 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("content-type", "utf-8");
        /**
         * 设置数据合理的缓存时间值，以避免浏览器频繁向服务器发送请求，提升服务器的性能
         * 这里是将数据的缓存时间设置为1天
         */
        resp.setDateHeader("expires", System.currentTimeMillis() + 24 * 3600 * 1000);
        String data = "对于不经常变化的数据，在servlet中可以为其设置合理的缓存时间值，以避免浏览器频繁向服务器发送请求，提升服务器的性能";

        resp.getOutputStream().write(data.getBytes());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
