package com.yckjsoft.javaee.servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yong on 2016/10/8 0008.
 * 不存在线程安全问题的代码：
 */
public class ServletDemo3_inner extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 当多线程并发访问这个方法里面的代码时，会存在线程安全问题吗
         * i变量被多个线程并发访问，但是没有线程安全问题，因为i是doGet方法里面的局部变量，
         * 当有多个线程并发访问doGet方法时，每一个线程里面都有自己的i变量，
         * 各个线程操作的都是自己的i变量，所以不存在线程安全问题
         * 多线程并发访问某一个方法的时候，如果在方法内部定义了一些资源(变量，集合等)
         * 那么每一个线程都有这些东西，所以就不存在线程安全问题了
         */

        int i = 1;
        i++;
        resp.getWriter().write(i + "");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
