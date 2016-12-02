package com.yckjsoft.javaee.servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yong on 2016/10/8 0008.
 *
 * 存在线程安全问题的代码
 *
 * 线程安全问题只存在多个线程并发操作同一个资源的情况下，
 * 所以在编写Servlet的时候，如果并发访问某一个资源(变量，集合等)，就会存在线程安全问题
 */
public class ServletDemo3 extends HttpServlet {
    int i = 1;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        i++;
        try {
            Thread.sleep(1000 * 4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        resp.getWriter().write(i + "");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
