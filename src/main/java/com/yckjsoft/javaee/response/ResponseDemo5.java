package com.yckjsoft.javaee.response;


import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

/**
 * Created by yong on 2016/10/8 0008.
 * <p>
 */
//一个web资源收到客户端请求后，通知客户端去访问另外一个web资源，这称之为请求重定向。
//应用场景：用户登陆，用户首先访问登录页面，登录成功后，就会跳转到某个页面，
// 这个过程就是一个请求重定向的过程
//
// 实现方式：response.sendRedirect(String location)，
// 即调用response对象的sendRedirect方法实现请求重定向
// sendRedirect内部的实现原理：使用response设置302状态码和设置location响应头实现重定向
public class ResponseDemo5 extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 1.调用sendRedirect方法实现请求重定向,
         * sendRedirect方法内部调用了
         * response.setHeader("Location", "/JavaWeb_HttpServletResponse_Study_20140615/index.jsp");
         * response.setStatus(HttpServletResponse.SC_FOUND);
         * //设置302状态码，等同于response.setStatus(302);
         */
        resp.sendRedirect(req.getContextPath() + "/index.jsp");

        //2.使用response设置302状态码和设置location响应头实现重定向实现请求重定向
        //response.setHeader("Location", "/JavaWeb_HttpServletResponse_Study_20140615/index.jsp");
        //response.setStatus(HttpServletResponse.SC_FOUND);//设置302状态码，等同于response.setStatus(302);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
