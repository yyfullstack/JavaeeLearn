package com.login.web.controller;



import com.login.domain.User;
import com.login.service.IUserService;
import com.login.service.impl.UserServiceImpl;
import com.login.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yong on 2016/10/31 0031.
 * 处理用户登录的servlet
 */
public class LoginServletNew extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String userPwd = req.getParameter("userPwd");
        IUserService service = new UserServiceImpl();
        User user = service.loginUser(userName, userPwd);
        if (user == null) {

            String message = String.format("对不起，用户名或密码有误！！请重新登录！2秒后为您自动跳到登录页面！！" +
                    "<meta http-equiv='refresh' content='2;url=%s'/>", req.getContextPath() + "/servlet/LoginUIServlet");
            req.setAttribute("message", message);
            return;
        }
        //登录成功
        req.getSession().setAttribute("user", user);
        sendAutoLoginCookie(req, resp, user);
        String message = String.format("恭喜：%s,登陆成功！本页将在3秒后跳到首页！！" +
                        "<meta http-equiv='refresh' content='3;url=%s'",
                user.getUserName(), req.getContextPath() +  "/index.jsp");
        req.setAttribute("message", message);
        req.getRequestDispatcher("/message.jsp").forward(req, resp);

    }

    private void sendAutoLoginCookie(HttpServletRequest req, HttpServletResponse resp, User user) {
        if (req.getParameter("logintime") != null) {
            int logintime = Integer.parseInt(req.getParameter("logintime"));
            //创建cookie,cookie的名字是autologin，值是用户登录的用户名和密码，用户名和密码之间使用.进行分割，密码经过md5加密处理
            Cookie cookie = new Cookie("autologin", user.getUserName() + "." + WebUtils.md5(user.getUserPwd()));
            //设置cookie的有效期
            cookie.setMaxAge(logintime);
            //设置cookie的有效路径
            cookie.setPath(req.getContextPath());
            //将cookie写入到客户端浏览器
            resp.addCookie(cookie);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
