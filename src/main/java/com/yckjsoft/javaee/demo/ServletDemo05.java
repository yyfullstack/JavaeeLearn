package com.yckjsoft.javaee.demo;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by yong on 2016/10/8 0008.
 * 设置content-disposition响应头，让浏览器下载文件
 */
public class ServletDemo05 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 设置content-disposition响应头，让浏览器下载文件
         */
        resp.setHeader("content-disposition", "attachment;filename=cat.jpg");
        InputStream in = this.getServletContext().getResourceAsStream("images/cat.jpg");

        byte[] buffer = new byte[1024];
        int len = 0;
        //得到输出流
        OutputStream out = resp.getOutputStream();
        //读取输入流里面的内容存储到缓存区 buffer
        while ((len = in.read(buffer)) > 0) {
            //将缓存区里面的内容输出到浏览器中
            out.write(buffer, 0, len);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
