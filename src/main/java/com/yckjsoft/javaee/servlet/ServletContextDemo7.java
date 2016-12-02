package com.yckjsoft.javaee.servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * Created by yong on 2016/10/8 0008.
 * <p>
 * 使用类装载器读取资源文件
 * 通过类装载器读取资源文件的注意事项:不适合装载大文件，否则会导致jvm内存溢出
 */
public class ServletContextDemo7 extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("content-type", "text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
//        out.println(readSrcPropConfig() + "<hr/>");
//        out.println(readSrcDirPropConfig() + "<hr/>");
        readAVI2();


    }

    private String readSrcPropConfig() {
        //获取到装载当前类的类装载器
        ClassLoader loader = ServletContextDemo7.class.getClassLoader();
        //用类装载器读取src目录下的com.yckjsoft.javaee.servlet包中的config4.properties配置文件
        InputStream in = loader.getResourceAsStream("com/yckjsoft/javaee/servlet/config4.properties");
        Properties prop = new Properties();
        String result = "";
        try {
            prop.load(in);

            Iterator iterator = prop.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                Object key = entry.getKey();
                Object value = entry.getValue();
                result += (key + " = " + value) + "<br/>";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String readSrcDirPropConfig() {
        //获取到装载当前类的类装载器
        ClassLoader loader = ServletContextDemo7.class.getClassLoader();
        //用类装载器读取src目录下的config1.properties配置文件
        InputStream in = loader.getResourceAsStream("config1.properties");
        Properties prop = new Properties();
        String result = "";
        try {
            prop.load(in);
            Iterator iterator = prop.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                Object key = entry.getKey();
                Object value = entry.getValue();
                result += (key + " = " + value) + "<br/>";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 通过类装载器读取资源文件的注意事项:不适合装载大文件，否则会导致jvm内存溢出
     */
    private void readAVI() {
        //获取到装载当前类的类装载器
        ClassLoader loader = ServletContextDemo7.class.getClassLoader();
        URL path = loader.getResource("/001.avi");
        System.out.println("path = " + path.getPath());
        /**
         * 001.avi是一个150多M的文件，使用类加载器去读取这个大文件时会导致内存溢出：
         * java.lang.OutOfMemoryError: Java heap space
         */
        InputStream in = loader.getResourceAsStream("001.avi");
        System.out.println("in = " + in);
    }

    /**
     * 读取001.avi,并拷贝到e:\根目录下
     * 001.avi文件太大，只能用servletContext去读取
     */
    private void readAVI2() {
        //获取到装载当前类的类装载器
        String path = this.getServletContext().getRealPath("/001.avi");
        //获取文件名
        String filename = path.substring(path.lastIndexOf("\\") + 1);

        InputStream in = this.getServletContext().getResourceAsStream("/001.avi");
       /* try {
            System.out.println("in.available() = " + in.available());
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        byte[] buffer = new byte[1024];
        int len = 0;
        OutputStream out = null;
        try {
            out = new FileOutputStream("d:\\" + filename);
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            out.close();
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
