package com.yckjsoft.javaee.request;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.Map;

/**
 * Created by yong on 2016/10/8 0008.
 * <p>
 */
public class RequestDemo4 extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        testGetParameterMap(req, resp);
    }
    //在服务器端使用getParameterNames方法接收表单参数
    private void testGetParameterNames(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 客户端是以UTF-8编码传输数据到服务器端的，
        // 所以需要设置服务器端以UTF-8的编码进行接收，否则对于中文数据就会产生乱码
        // System.out.println("characterEncoding = " + req.getCharacterEncoding());
        req.setCharacterEncoding("utf-8");
        Enumeration<String> paramNames = req.getParameterNames();
        String htmlStr = "";
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            String paramValue = req.getParameter(paramName);
            htmlStr += MessageFormat.format("{0}={1}", paramName, paramValue) + "<br/>";
        }
        resp.setCharacterEncoding("utf-8");
        resp.setHeader("content-type", "text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.write(htmlStr);
    }

    //在服务器端使用getParameterMap方法接收表单参数
    private void testGetParameterMap(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //System.out.println("characterEncoding = " + req.getCharacterEncoding());
        req.setCharacterEncoding("utf-8");
        Map<String, String[]> paramMap = req.getParameterMap();
        String htmlStr = "";
        for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
            String paramName = entry.getKey();
            String paramValue = "";
            String[] paramValueArr = entry.getValue();
            for (int i = 0; paramValueArr != null && i < paramValueArr.length; i++) {
                if (i == paramValueArr.length - 1) {
                    paramValue += paramValueArr[i];
                } else {
                    paramValue += paramValueArr[i] + ",";
                }
            }
            htmlStr += MessageFormat.format("{0}={1}", paramName, paramValue) + "<br/>";
        }
        resp.setCharacterEncoding("utf-8");
        resp.setHeader("content-type", "text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.write(htmlStr);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
