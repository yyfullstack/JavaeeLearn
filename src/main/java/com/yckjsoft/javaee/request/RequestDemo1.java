package com.yckjsoft.javaee.request;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by yong on 2016/10/8 0008.
 * <p>
 * 通过request对象获取客户端请求信息
 */
public class RequestDemo1 extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取客户机信息
        //获取请求的URL地址
        String requestUrl = req.getRequestURL().toString();
        //得到请求的资源
        String requestUri = req.getRequestURI();
        //得到请求URL地址中附带的参数
        String queryString = req.getQueryString();
        //得到来访者的IP地址
        String remoteAddr = req.getRemoteAddr();
        //Host
        String remoteHost = req.getRemoteHost();
        //Port
        int remotePort = req.getRemotePort();
        //User
        String remoteUser = req.getRemoteUser();
        //Method 得到请求URL地址时使用的方法
        String method = req.getMethod();

        String pathInfo = req.getPathInfo();

        //localAddr  获取web服务器的IP地址
        String localAddr = req.getLocalAddr();
        //localName  获取web服务器的主机名
        String localName = req.getLocalName();
        resp.setCharacterEncoding("utf-8");

        resp.setHeader("content-type", "text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.write("获取客户机信息如下:");
        out.write("<hr/>");
        out.write("请求的URL地址：" + requestUrl + "<br/>");
        out.write("请求的资源：" + requestUri + "<br/>");
        out.write("请求的URL地址中附带的参数：" + queryString + "<br/>");
        out.write("来访者的IP地址：" + remoteAddr + "<br/>");
        out.write("来访者的主机名：" + remoteHost + "<br/>");
        out.write("使用的端口号：" + remotePort + "<br/>");
        out.write("remoteUser：" + remoteUser + "<br/>");
        out.write("请求的使用的方法：" + method + "<br/>");
        out.write("pathInfo：" + pathInfo + "<br/>");
        out.write("localAddr：" + localAddr + "<br/>");
        out.write("localName：" + localName + "<br/>");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
