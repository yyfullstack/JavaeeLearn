package com.yckjsoft.javaee.session.share;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yong on 2016/10/25 0025.
 */
public class BuyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Book book = DB.getAll().get(id);
        HttpSession session = req.getSession();
        List<Book> list = (List<Book>) session.getAttribute("list");
        if (list == null) {
            list = new ArrayList<Book>();
            session.setAttribute("list", list);
        }
        list.add(book);

        String url = resp.encodeRedirectURL(req.getContextPath() + "/listCartServlet");
        System.out.println("url = " + url);
        resp.sendRedirect(url);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
