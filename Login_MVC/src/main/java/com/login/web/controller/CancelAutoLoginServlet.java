package com.login.web.controller;


import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yong on 2016/10/31 0031.
 */
public class CancelAutoLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //移除存储在session中的user
        req.getSession().removeAttribute("user");
        //移除自动登录的cookie
        removeAutoLoginCookie(req, resp);
        //注销用户后跳转到登录页面
        req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);
    }

    /**
     * 删除自动登录cookie，
     * JavaWeb中删除cookie的方式就是新创建一个cookie，新创建的cookie与要删除的cookie同名，
     * 设置新创建的cookie的cookie的有效期设置为0，有效路径与要删除的cookie的有效路径相同
     *
     * @param req
     * @param resp
     */
    private void removeAutoLoginCookie(HttpServletRequest req, HttpServletResponse resp) {
        //创建一个名字为autologin的cookie
        Cookie cookie = new Cookie("autologin", "");
        //将cookie的有效期设置为0，命令浏览器删除该cookie
        cookie.setMaxAge(0);
        //设置要删除的cookie的path
        cookie.setPath(req.getContextPath());
        resp.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
