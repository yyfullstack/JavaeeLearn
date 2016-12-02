package com.yckjsoft.javaee.session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yong on 2016/10/25 0025.
 * 表单重复提交的常见应用场景
 */
public class FormSubmitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置服务器端以UTF-8编码进行输出
//        req.setCharacterEncoding("UTF-8");
//        String username = req.getParameter("username");
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("插入的数据是： " + username);


        boolean b = isRepeatSubmit(req);
        if (b == true) {
            System.out.println("请不要重复提交");
            return;
        }
        //移除session中的token
        req.getSession().removeAttribute("token");

        System.out.println("处理用户的提交请求");

    }

    private boolean isRepeatSubmit(HttpServletRequest req) {
        String client_token = req.getParameter("token");
        //1、如果用户提交的表单数据中没有token，则用户是重复提交了表单
        if (client_token == null) {
            return true;
        }
        //取出存储在session中的token
        String server_token = (String) req.getSession().getAttribute("token");
        //2、如果当前用户的Session中不存在Token(令牌)，则用户是重复提交了表单
        if (server_token == null) {
            return true;
        }
        //3、存储在Session中的Token(令牌)与表单提交的Token(令牌)不同，则用户是重复提交了表单
        if (!client_token.equalsIgnoreCase(server_token)) {
            return true;
        }
        return false;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
