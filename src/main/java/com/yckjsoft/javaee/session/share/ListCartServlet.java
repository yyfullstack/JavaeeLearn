package com.yckjsoft.javaee.session.share;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by yong on 2016/10/25 0025.
 */
public class ListCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        //创建session
        HttpSession session = req.getSession();
        List<Book> list = (List<Book>) session.getAttribute("list");
        if (list == null || list.size() == 0) {
            out.write("对不起，您没有购买任何商品！");
            return;
        }
        out.write("您购买如下商品：<br/>");

        for (Book book: list) {
            out.println(book.getName() + "<br/>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
