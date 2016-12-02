package com.yckjsoft.javaee.response;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by yong on 2016/10/8 0008.
 * <p>
 * 使用OutputStream流向客户端浏览器输出"中国"这两个汉字
 * <p>
 * 使用OutputStream流输出中文注意问题：
 * <p>
 * 在服务器端，数据是以哪个码表输出的，那么就要控制客户端浏览器以相应的码表打开，
 * 比如：outputStream.write("中国".getBytes("UTF-8"));使用OutputStream流向客户端浏览器输出中文，
 * 以UTF-8的编码进行输出，此时就要控制客户端浏览器以UTF-8的编码打开，否则显示的时候就会出现中文乱码，
 * 那么在服务器端如何控制客户端浏览器以以UTF-8的编码显示数据呢？可以通过设置响应头控制浏览器的行为，
 * 例如：response.setHeader("content-type", "text/html;charset=UTF-8");
 * 通过设置响应头控制浏览器以UTF-8的编码显示数据。
 */
public class ResponseDemo1 extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //outputChineseByOutputStream(resp);
        outputNumberByOutputStream(resp);
    }
    /**使用OutputStream输出中文注意问题：
     * 在服务器端，数据是以哪个码表输出的，那么就要控制客户端浏览器以相应的码表打开，
     * 比如：outputStream.write("中国".getBytes("UTF-8"));
     * //使用OutputStream流向客户端浏览器输出中文，以UTF-8的编码进行输出
     * 此时就要控制客户端浏览器以UTF-8的编码打开，否则显示的时候就会出现中文乱码，那么在服务器端如何控制客户端浏览器以以UTF-8的编码显示数据呢？
     * 可以通过设置响应头控制浏览器的行为，例如：
     * response.setHeader("content-type", "text/html;charset=UTF-8");//通过设置响应头控制浏览器以UTF-8的编码显示数据
     */
    /**
     * data.getBytes()是一个将字符转换成字节数组的过程，这个过程中一定会去查码表，
     * 如果是中文的操作系统环境，默认就是查找查GB2312的码表，
     * 将字符转换成字节数组的过程就是将中文字符转换成GB2312的码表上对应的数字
     * 比如： "中"在GB2312的码表上对应的数字是98
     * "国"在GB2312的码表上对应的数字是99
     * <p>
     */
    private void outputChineseByOutputStream(HttpServletResponse resp) {
        String data = "中国";
        //通过设置响应头控制浏览器以UTF-8的编码显示数据，如果不加这句话，那么浏览器显示的将是乱码
        resp.setHeader("content-type", "text/html;charset=UTF-8");
        try {
            OutputStream outputStream = resp.getOutputStream();//获取OutputStream输出流
            //getBytes()方法如果不带参数，那么就会根据操作系统的语言环境来选择转换码表，
            // 如果是中文操作系统，那么就使用GB2312的码表
            //将字符转换成字节数组，指定以UTF-8编码进行转换
            byte[] dataBytes = data.getBytes("UTF-8");
            outputStream.write(dataBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //使用OutputStream流输出数字1
    //在开发过程中，如果希望服务器输出什么浏览器就能看到什么，
    // 那么在服务器端都要以字符串的形式进行输出。
    private void outputNumberByOutputStream(HttpServletResponse resp) {
        int i = 100;
        resp.setHeader("content-type", "text/html;charset=UTF-8");
        try {
            OutputStream outputStream = resp.getOutputStream();
            outputStream.write("使用OutputStream输出数字：".getBytes("utf-8"));
            //outputStream.write(i); //运行的结果和我们想象中的不一样，数字100没有输出来

            //这一步是将数字i和一个空字符串相加，这样处理之后，数字i就变成了字符串i了，
            //然后再将字符串1转换成字节数组使用OutputStream进行输出
            outputStream.write((i + "").getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
