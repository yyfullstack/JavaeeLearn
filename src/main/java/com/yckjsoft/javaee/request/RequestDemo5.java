package com.yckjsoft.javaee.request;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yong on 2016/10/8 0008.
 * <p>
 *     以POST方式提交表单中文参数的乱码问题
 */
public class RequestDemo5 extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //req.setCharacterEncoding("UTF-8");
        String userName = req.getParameter("username");
        //获取request对象以ISO8859-1字符编码接收到的原始数据的字节数组，
        // 然后通过字节数组以指定的编码构建字符串，解决乱码问题
        System.out.println("get-->username：" + new String(userName.getBytes("ISO8859-1"), "utf-8"));
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String userName = req.getParameter("username");
        System.out.println("post-->username：" + userName);
    }
}
