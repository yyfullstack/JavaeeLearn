package com.yckjsoft.javaee.servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yong on 2016/10/8 0008.
 * <p>
 * 访问的是ServletContextDemo4，浏览器显示的却是ServletContextDemo5的内容，
 * 这就是使用ServletContext实现了请求转发
 */
public class ServletContextDemo5 extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getOutputStream().write("ServletContextDemo5".getBytes());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
