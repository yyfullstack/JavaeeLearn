package com.yckjsoft.javaee.session.share;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;

/**
 * Created by yong on 2016/10/25 0025.
 * //首页：列出所有书
 */
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        //创建session
        //HttpSession session = req.getSession();
        out.write("本站图书如下：<br/>");
        Set<Map.Entry<String, Book>> set = DB.getAll().entrySet();

        for (Map.Entry<String, Book> me : set) {
            Book book = me.getValue();
            String url = req.getContextPath() + "/buyServlet?id=" + book.getId();
            url = resp.encodeURL(url);
            out.println(book.getName() + "<a href='" + url + "'>购买</a><br/>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
