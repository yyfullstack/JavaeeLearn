package com.yckjsoft.javaee.session.token;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yong on 2016/10/25 0025.
 * 在TokenServlet中生成Token之后再重定向到form.jsp页面
 */
public class TokenServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = TokenProccessor.getInstance().makeToken();//创建令牌
        System.out.println("在TokenServlet中生成的token = " + token);
        req.getSession().setAttribute("token", token);
        req.getRequestDispatcher("/demo/session/form_resubmit.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }


}
