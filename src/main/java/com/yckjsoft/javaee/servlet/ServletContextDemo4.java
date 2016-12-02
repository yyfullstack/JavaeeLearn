package com.yckjsoft.javaee.servlet;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yong on 2016/10/8 0008.
 * 用servletContext实现请求转发
 * <p>
 */
public class ServletContextDemo4 extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String data = "<h1>servlecontext实现请求转发</h1>";
        resp.getOutputStream().write(data.getBytes());

        ServletContext context = this.getServletContext();
        //获取请求转发对象(RequestDispatcher)
        RequestDispatcher rd = context.getRequestDispatcher("/servlet/contextDemo5");
        //调用forward方法实现请求转发
        rd.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
