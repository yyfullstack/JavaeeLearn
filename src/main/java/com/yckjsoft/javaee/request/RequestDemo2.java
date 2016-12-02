package com.yckjsoft.javaee.request;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * Created by yong on 2016/10/8 0008.
 * <p>
 *     通过request对象获取客户端请求头信息
 */
public class RequestDemo2 extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置将字符以"UTF-8"编码输出到客户端浏览器
        resp.setCharacterEncoding("utf-8");
        //通过设置响应头控制浏览器以UTF-8的编码显示数据
        resp.setHeader("content-type", "text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        Enumeration<String> reqHeadInfos = req.getHeaderNames();

        out.write("获取客户端所有请求头信息如下:");
        out.write("<hr/>");
        while (reqHeadInfos.hasMoreElements()) {
            String headName = reqHeadInfos.nextElement();
            String headValue = req.getHeader(headName);
            out.write(headName + " ：" + headValue + "<br/>");
        }
        out.write("<br/>");
        //获取Accept-Encoding请求头对应的值
        String value = req.getHeader("Accept-Encoding");
        out.write(value);

        Enumeration<String> acceptEncodings = req.getHeaders("Accept-Encoding");
        while (acceptEncodings.hasMoreElements()) {
            String str = acceptEncodings.nextElement();
            System.out.println("str = " + str);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
