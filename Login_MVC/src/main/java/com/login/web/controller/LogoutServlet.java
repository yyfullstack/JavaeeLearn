package com.login.web.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.MessageFormat;

/**
 * Created by yong on 2016/10/31 0031.
 * 处理用户登录的servlet
 */
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //移除存在session中的user对象
        req.getSession().removeAttribute("user");

        String tempStr1 = MessageFormat.format("注销成功！！3秒后为您自动跳到登录页面！！" +
                "<meta http-equiv='refresh' content='3;url={0}'/>", req.getContextPath() + "/servlet/LoginUIServlet");
        System.out.println(tempStr1);
        System.out.println("-----------------------------------------");

        String tempStr2 = MessageFormat.format("注销成功！！3秒后为您自动跳到登录页面！！" +
                "<meta http-equiv=''refresh'' content=''3;url={0}''/>", req.getContextPath() + "/servlet/LoginUIServlet");

        System.out.println(tempStr2);

        String message = String.format("注销成功！！3秒后为您自动跳到登录页面！！" +
                "<meta http-equiv='refresh' content='3;url=%s'/>", req.getContextPath() + "/servlet/LoginUIServlet");
        System.out.println(message);
        req.setAttribute("message", message);
        req.getRequestDispatcher("/message.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
