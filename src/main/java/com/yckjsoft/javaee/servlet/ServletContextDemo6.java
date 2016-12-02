package com.yckjsoft.javaee.servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * Created by yong on 2016/10/8 0008.
 * <p>
 * 使用servletContext读取资源文件
 */
public class ServletContextDemo6 extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * response.setContentType("text/html;charset=UTF-8");目的是控制浏览器用UTF-8进行解码；
         * 这样就不会出现中文乱码了
         */
        resp.setHeader("content-type", "text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
//        out.println(readPropCfgFile1() + "<hr/>");
//        out.println(readPropCfgFile2() + "<hr/>");
//        out.println(readSrcDirPropConfig() + "<hr/>");
        out.println(readWebRootPropConfig() + "<hr/>");


    }
    //读取src目录下的com.yckjsoft.javaee.servlet包中的config4.properties配置文件
    private String readPropCfgFile1() {
        InputStream in = this.getServletContext().getResourceAsStream("/WEB-INF/classes/com/yckjsoft/javaee/servlet/config4.properties");
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
    //读取src目录下的db.config包中的config3.properties配置文件
    private String readPropCfgFile2() {
        //通过ServletContext获取web资源的绝对路径
        String path = this.getServletContext().getRealPath("/WEB-INF/classes/db/config/config3.properties");
        Properties prop = new Properties();
        String result = "";
        InputStream in = null;
        try {
            in = new FileInputStream(path);
            prop.load(in);
            Iterator iterator = prop.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                Object key = entry.getKey();
                Object value = entry.getValue();
                result += (key + " = " + value) + "<br/>";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    //通过ServletContext对象读取src目录下的properties配置文件
    private String readSrcDirPropConfig() {
        InputStream in = this.getServletContext().getResourceAsStream("/WEB-INF/classes/config1.properties");

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
    //通过ServletContext对象读取WebRoot目录下的properties配置文件
    private String readWebRootPropConfig() {
        InputStream in = this.getServletContext().getResourceAsStream("/config2.properties");
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
