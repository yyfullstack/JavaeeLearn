package com.yckjsoft.javaee.demo;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yong on 2016/10/8 0008.
 * 设置refresh响应头，让浏览器定时刷新
 */
public class ServletDemo04 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置refresh响应头，让浏览器每隔3秒定时刷新
//        resp.setHeader("refresh", "3");

        //设置refresh响应头，让浏览器3秒后跳转到http://www.baidu.com
        resp.setHeader("refresh", "3;url='https://www.baidu.com'");
        resp.getWriter().write("gacl");


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }


}
