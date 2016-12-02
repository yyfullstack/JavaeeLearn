package com.yckjsoft.javaee.response;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by yong on 2016/10/8 0008.
 * 使用PrintWriter流向客户端浏览器输出中文数据
 * <p>
 * 使用PrintWriter流输出中文注意问题：
 * <p>
 * 在获取PrintWriter输出流之前首先使用"response.setCharacterEncoding(charset)"设置字符以什么样的编码输出到浏览器，
 * 如：response.setCharacterEncoding("UTF-8");设置将字符以"UTF-8"编码输出到客户端浏览器，
 * 然后再使用response.getWriter();获取PrintWriter输出流，这两个步骤不能颠倒，如下：
 * response.setCharacterEncoding("UTF-8");//设置将字符以"UTF-8"编码输出到客户端浏览器
 * PrintWriter out = response.getWriter();这句代码必须放在response.setCharacterEncoding("UTF-8");之后
 * 否则response.setCharacterEncoding("UTF-8")这行代码的设置将无效，浏览器显示的时候还是乱码
 * PrintWriter out = response.getWriter();//获取PrintWriter输出流
 */
public class ResponseDemo2 extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //outputChineseByWriter(resp);
        outputNumberByWriter(resp);

    }
    /**使用PrintWriter流输出中文注意问题：
     * 在获取PrintWriter输出流之前首先使用"response.setCharacterEncoding(charset)"设置字符以什么样的编码输出到浏览器，
     * 如：response.setCharacterEncoding("UTF-8");设置将字符以"UTF-8"编码输出到客户端浏览器，然后再使用response.getWriter();
     * 获取PrintWriter输出流，这两个步骤不能颠倒
     */
    /**
     * 然后再使用response.setHeader("content-type", "text/html;charset=字符编码");设置响应头，
     * 控制浏览器以指定的字符编码编码进行显示，
     */
    private void outputChineseByWriter(HttpServletResponse resp) {
        String data = "中国";
        //设置将字符以"UTF-8"编码输出到客户端浏览器
        resp.setCharacterEncoding("UTF-8");
        /**
         * PrintWriter out = response.getWriter();这句代码必须放在response.setCharacterEncoding("UTF-8");之后
         * 否则response.setCharacterEncoding("UTF-8")这行代码的设置将无效，浏览器显示的时候还是乱码
         */
        try {
            PrintWriter out = resp.getWriter();//获取PrintWriter输出流
            /**
             * 多学一招：使用HTML语言里面的<meta>标签来控制浏览器行为，模拟通过设置响应头控制浏览器行为
             * out.write("<meta http-equiv='content-type' content='text/html;charset=UTF-8'/>");
             * 等同于response.setHeader("content-type", "text/html;charset=UTF-8");
             */
            //out.write("<meta http-equiv='content-type' content='text/html;charset=UTF-8'/>");
            resp.setHeader("content-type", "text/html;charset=UTF-8");
            //使用PrintWriter流向客户端输出字符
            out.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //如果使用PrintWriter流输出数字，那么也要先将数字转换成字符串后再输出，
    private void outputNumberByWriter(HttpServletResponse resp) {
        int i = 100;
        resp.setHeader("content-type", "text/html;charset=UTF-8");
        resp.setCharacterEncoding("utf-8");
        try {
            PrintWriter out = resp.getWriter();
            out.write("使用PrintWriter输出数字：");
            out.write(i + "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
