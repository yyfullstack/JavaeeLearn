package com.yckjsoft.javaee.cookie;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created by yong on 2016/10/25 0025.
 * cookie实例：获取用户上一次访问的时间
 */
public class CookieDemo1 extends HttpServlet {
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
                if (cookie.getName().equalsIgnoreCase("lastAccessTime")) {
                    Long lastAccessTime = Long.parseLong(cookie.getValue());
                    Date date = new Date(lastAccessTime);
                    out.write(date.toLocaleString());

                }
            }
        } else {
            out.write("这是您第一次访问本站！");
        }
        //用户访问过之后，重新设置用户的访问时间，存储到cookie中，然后发送到客户端浏览器
        //创建一个cookie，cookie的名字是lastAccessTime
        Cookie cookie = new Cookie("lastAccessTime", System.currentTimeMillis() + "");
        //设置cookie 的有效期为1天，单位是秒
        //cookie.setMaxAge(24*60*60);
        //将cookie的有效期设置为0，命令浏览器删除该cookie
        cookie.setMaxAge(0);

        //将cookie对象添加到response对象中，这样服务器在输出response对象中的内容时将会把cookie也输出到客户端浏览器
        resp.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
