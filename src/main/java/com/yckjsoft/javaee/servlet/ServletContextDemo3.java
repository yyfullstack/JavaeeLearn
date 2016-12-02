package com.yckjsoft.javaee.servlet;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yong on 2016/10/8 0008.
 * <p>
 * 获取WEB应用的初始化参数
 * <p>
 * 在web.xml文件中使用<context-param>标签配置WEB应用的初始化参数
 */
public class ServletContextDemo3 extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext context = this.getServletContext();
        //获取整个web站点的初始化参数
        String data = context.getInitParameter("url");
        resp.getWriter().print("data=" + data);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
