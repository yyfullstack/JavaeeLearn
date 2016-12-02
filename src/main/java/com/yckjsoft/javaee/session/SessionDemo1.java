package com.yckjsoft.javaee.session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by yong on 2016/10/25 0025.
 * 服务器是如何实现一个session为一个用户浏览器服务的？
 */
public class SessionDemo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置服务器端以UTF-8编码进行输出
        resp.setCharacterEncoding("UTF-8");
        //设置浏览器以UTF-8编码格式进行接收，解决中文乱码问题
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();

        //使用request对象的getSession，来获取session，如果session不存在则创建一个
        HttpSession session = req.getSession();
        //将数据存储到session中
        session.setAttribute("data", "国产001");
        //获取sessionID
        String sessionId = session.getId();
        //判断session是不是新创建的
        if (session.isNew()) {
            out.write("session创建成功，session的id是：" + sessionId);
        } else {
            out.write("服务器已经存在该session了，session的id是：" + sessionId);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
