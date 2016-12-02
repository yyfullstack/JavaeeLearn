package com.yckjsoft.javaee.cookie;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by yong on 2016/10/25 0025.
 * cookie中存取中文
 */
public class CookieDemo2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置服务器端以UTF-8编码进行输出
        resp.setCharacterEncoding("UTF-8");
        //设置浏览器以UTF-8编码格式进行接收，解决中文乱码问题
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        //获取浏览器访问服务器时传递过来的cookie数组
        Cookie[] cookies = req.getCookies();

        //如果用户是第一次访问，那么得到的cookies将是null
        if (cookies != null) {
            out.write("您上次访问的时间是：");
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equalsIgnoreCase("username")) {
                    String username = URLDecoder.decode(cookies[i].getValue(), "UTF-8");
                    out.write(",访问者：" + username);

                }
            }
        } else {
            out.write("这是您第一次访问本站！");
        }
        //用户访问过之后，重新设置用户的访问时间，存储到cookie中，然后发送到客户端浏览器
        //创建一个cookie，cookie的名字是lastAccessTime
        //要想在cookie中存储中文，那么必须使用URLEncoder类里面的encode(String s, String enc)方法进行中文转码
        Cookie chineseCookie = new Cookie("username", URLEncoder.encode("穆拉丁", "utf-8"));
        //设置cookie 的有效期为1天，单位是秒
        //chineseCookie.setMaxAge(24*60*60);
        //将cookie的有效期设置为0，命令浏览器删除该cookie
        //chineseCookie.setMaxAge(0);

        //将cookie对象添加到response对象中，这样服务器在输出response对象中的内容时将会把cookie也输出到客户端浏览器
        resp.addCookie(chineseCookie);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
