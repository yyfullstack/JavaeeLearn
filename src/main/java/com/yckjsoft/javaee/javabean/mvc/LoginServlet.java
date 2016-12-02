package com.yckjsoft.javaee.javabean.mvc;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yong on 2016/10/8 0008.
 * <p>
 *     控制器(controller)
 */
public class LoginServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        String submitFlag = req.getParameter("submitFlag");
        if ("toLogin".equals(submitFlag)) {
            toLogin(req, resp);
            return;
        } else if ("login".equals(submitFlag)) {
            login(req, resp);
            return;
        }
        toLogin(req, resp);
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //接收参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //2.验证并封装参数
        UserBean user = new UserBean();
        user.setUsername(username);
        user.setPassword(password);
        //3.调用javabean对象的业务方法
        if (user.login()) {
            //4.根据返回这选择下一个界面
            resp.sendRedirect("/demo/javabean/mvc/success.jsp");
        } else {
            //登录失败在返回登录页面并显示上次输入的用户名
            //将视图要显示的模型数据放在请求里传递给视图，实体再来展示
            //此处也可以看出和servlet api 紧密耦合，更换视图技术也需要重新设置这些数据
            req.setAttribute("user", user);
            toLogin(req, resp);
            return;
        }
    }

    private void toLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/demo/javabean/mvc/login.jsp").forward(req, resp);
    }
}
