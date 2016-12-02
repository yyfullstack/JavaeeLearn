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
 * 先运行ServletContextDemo1，将数据data存储到ServletContext对象中，
 * 然后运行ServletContextDemo2就可以从ServletContext对象中取出数据了，
 * 这样就实现了数据共享
 */
public class ServletContextDemo2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext context = this.getServletContext();
        //从ServletContext对象中取出数据
        String data = (String) context.getAttribute("data");
        resp.getWriter().print("data=" + data);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
