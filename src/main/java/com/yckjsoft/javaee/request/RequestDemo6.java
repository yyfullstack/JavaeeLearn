package com.yckjsoft.javaee.request;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yong on 2016/10/8 0008.
 * <p>
 * 请求RequestDemo06 Servlet，RequestDemo06将请求转发到test.jsp页面
 */
public class RequestDemo6 extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String data = "我正在学习javaweb";
        /**
         * 将数据存放到request对象中,此时把request对象当作一个Map容器来使用
         */
        req.setAttribute("data", data);
        //客户端访问RequestDemo06这个Servlet后，RequestDemo06通知服务器将请求转发(forward)到test.jsp页面进行处理
        req.getRequestDispatcher("/demo/request/test.jsp").forward(req, resp);

        //System.out.println("少时诵诗书");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
