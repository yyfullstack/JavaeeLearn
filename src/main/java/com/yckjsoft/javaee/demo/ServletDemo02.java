package com.yckjsoft.javaee.demo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

/**
 * Created by yong on 2016/10/8 0008.
 * 设置Content-Encoding响应头，告诉浏览器数据的压缩格式
 * 这个小程序是用来演示以下两个小知识点
 * 1、使用GZIPOutputStream流来压缩数据
 * 2、设置响应头Content-Encoding来告诉浏览器，服务器发送回来的数据压缩后的格式
 */
public class ServletDemo02 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String data = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb" +
                "ccccccccccccccccccccccccccccccccccccccccc" +
                "dddddddddddddddddddddddddddddddddddddddddddd" +
                "ffffffffffffffffffffffffffffffffffffffffffffffff" +
                "ccccccccccccccccccccccccccccccccccccccccccccccccccc" +
                "gggggggggggggggggggggggggggggggggggggggggggggggggggg" +
                "hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh";
        System.out.println("原始数据的大小为 : = " + data.getBytes().length);

        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        GZIPOutputStream gout = new GZIPOutputStream(bout);
        //数据流写入gout中
        gout.write(data.getBytes());
        gout.close();

        //得到压缩后的数据
        byte[] g = bout.toByteArray();
        resp.setHeader("Content-Encoding", "gzip");
        resp.setHeader("Content-Length", g.length + "");
        resp.getOutputStream().write(g);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
