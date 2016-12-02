package com.yckjsoft.javaee.response;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * Created by yong on 2016/10/8 0008.
 * <p>
 * getOutputStream和getWriter方法分别用于得到输出二进制数据、输出文本数据的ServletOuputStream、Printwriter对象。
 * getOutputStream和getWriter这两个方法互相排斥，调用了其中的任何一个方法后，就不能再调用另一方法。
 * Servlet程序向ServletOutputStream或PrintWriter对象中写入的数据将被Servlet引擎从response里面获取，
 * Servlet引擎将这些数据当作响应消息的正文，然后再与响应状态行和各响应头组合后输出到客户端。
 * Serlvet的service方法结束后，Servlet引擎将检查getWriter或getOutputStream方法返回的输出流对象是否已经调用过close方法，
 * 如果没有，Servlet引擎将调用close方法关闭该输出流对象。
 * <p>
 * 文件下载:
 * 文件下载功能是web开发中经常使用到的功能，使用HttpServletResponse对象就可以实现文件的下载
 * <p>
 * 文件下载功能的实现思路：
 * <p>
 * 　　1.获取要下载的文件的绝对路径
 * <p>
 * 　　2.获取要下载的文件名
 * <p>
 * 　　3.设置content-disposition响应头控制浏览器以下载的形式打开文件
 * <p>
 * 　　4.获取要下载的文件输入流
 * <p>
 * 　　5.创建数据缓冲区
 * <p>
 * 　　6.通过response对象获取OutputStream流
 * <p>
 * 　　7.将FileInputStream流写入到buffer缓冲区
 * <p>
 * 　　8.使用OutputStream将缓冲区的数据输出到客户端浏览器
 *
 *
 * 下载中文文件时，需要注意的地方就是中文文件名要使用URLEncoder.encode方法进行
 * 编码(URLEncoder.encode(fileName, "字符编码"))，否则会出现文件名乱码。
 *
 * 编写文件下载功能时推荐使用OutputStream流，避免使用PrintWriter流，因为OutputStream流是字节流，可以处理任意类型的数据，
 * 而PrintWriter流是字符流，只能处理字符数据，如果用字符流处理字节数据，会导致数据丢失。
 *
 *
 */
public class ResponseDemo3 extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        downloadFileByOutputStream(resp);
//        downloadFileByOutputStream2(resp);
//        downloadFileByPrintWriter(resp);
    }

    /**
     * 下载文件，通过OutputStream流
     *
     * @param resp
     */
    private void downloadFileByOutputStream(HttpServletResponse resp) {
        InputStream in = null;
        OutputStream out = null;
        //1.获取要下载的文件绝对路径
        String realPath = this.getServletContext().getRealPath("/images/cat.jpg");
        //2.获取要下载的文件名
        String filename = realPath.substring(realPath.lastIndexOf("\\") + 1);
        //3.设置content-disposition 响应控制浏览器以下载的形式打开文件
        resp.setHeader("content-disposition", "attachment;filename=" + filename);
        //4.获取要下载的文件输入流
        try {
            in = new FileInputStream(realPath);
            int len = 0;
            //5.创建数据缓冲区
            byte[] buffer = new byte[1024];
            //6.通过response对象获取outputstream
            out = resp.getOutputStream();
            //7.将FileInputStream流写入到buffer缓冲区
            while ((len = in.read(buffer)) > 0) {
                //8.使用outputstream将缓冲区的数据输出到客户端浏览器
                out.write(buffer, 0, len);
            }
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 下载中文文件,中文文件下载时，文件名要经过URL编码，否则会出现文件名乱
     *
     * @param resp
     * @throws UnsupportedEncodingException
     */
    private void downloadFileByOutputStream2(HttpServletResponse resp) throws UnsupportedEncodingException {
        InputStream in = null;
        OutputStream out = null;
        //1.获取要下载的文件绝对路径
        String realPath = this.getServletContext().getRealPath("/images/cat.jpg");
        //2.获取要下载的文件名
        String filename = realPath.substring(realPath.lastIndexOf("\\") + 1);
        //3.设置content-disposition 响应控制浏览器以下载的形式打开文件
        // 中文文件名要使用URLEncoder.encode方法进行编码，否则会出现文件名乱码
        resp.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
        //4.获取要下载的文件输入流
        try {
            in = new FileInputStream(realPath);
            int len = 0;
            //5.创建数据缓冲区
            byte[] buffer = new byte[1024];
            //6.通过response对象获取outputstream
            out = resp.getOutputStream();
            //7.将FileInputStream流写入到buffer缓冲区
            while ((len = in.read(buffer)) > 0) {
                //8.使用outputstream将缓冲区的数据输出到客户端浏览器
                out.write(buffer, 0, len);
            }
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 下载文件，通过PrintWriter流，虽然也能够实现下载，但是会导致数据丢失，因此不推荐使用PrintWriter流下载文件
     *
     * @param resp
     */
    private void downloadFileByPrintWriter(HttpServletResponse resp) {
        FileReader in = null;
        PrintWriter out = null;
        //1.获取要下载的文件绝对路径
        String realPath = this.getServletContext().getRealPath("/images/小喵.jpg");
        //2.获取要下载的文件名
        String filename = realPath.substring(realPath.lastIndexOf("\\") + 1);
        //3.设置content-disposition 响应控制浏览器以下载的形式打开文件
        // 中文文件名要使用URLEncoder.encode方法进行编码，否则会出现文件名乱码
        try {
            resp.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //4.获取要下载的文件输入流
        try {
            in = new FileReader(realPath);
            int len = 0;
            //5.创建数据缓冲区
            char[] buffer = new char[1024];
            //6.通过response对象获取outputstream
            out = resp.getWriter();
            //7.将FileInputStream流写入到buffer缓冲区
            while ((len = in.read(buffer)) > 0) {
                //8.使用outputstream将缓冲区的数据输出到客户端浏览器
                out.write(buffer, 0, len);
            }
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
