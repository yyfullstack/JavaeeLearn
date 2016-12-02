package com.yckjsoft.javaee.demo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yong on 2016/10/8 0008.
 * 设置Location响应头，实现请求重定向
 */
public class ServletDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(302); //设置服务器响应状态码
        //设置响应头，服务器通过 Location这个头，来告诉浏览器跳到哪里，这就是所谓的请求重定向
        resp.setHeader("Location", "index.jsp");

        /*服务器返回一个302状态码告诉浏览器，你要的资源我没有，但是我通过Location响应头告诉你哪里有，
        而浏览器解析响应头Location后知道要跳转到/index.jsp页面， 所以就会自动跳转到index.jsp，*/
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
