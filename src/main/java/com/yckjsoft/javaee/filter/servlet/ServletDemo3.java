package com.yckjsoft.javaee.filter.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by yong on 2016/11/21 0021.
 */
public class ServletDemo3 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");//名字
        String text = req.getParameter("textarea");//浏览内容
        PrintWriter pw = resp.getWriter();
        pw.println("name=" + name);//这里直接输出，仅仅只是为了查看能否过滤那些关键字。。
        pw.print("内容" + text);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
