package com.yckjsoft.javaee.servlet;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by yong on 2016/10/8 0008.
 * <p>
 */
public class ServletConfigDemo1 extends HttpServlet {
    //定义ServletConfig对象来接收配置的初始化参数
    private ServletConfig config;

    /**
     * 当servlet配置了初始化参数后，web容器在创建servlet实例对象时，
     * 会自动将这些初始化参数封装到ServletConfig对象中，并在调用servlet的init方法时，
     * 将ServletConfig对象传递给servlet。进而，程序员通过ServletConfig对象就可以
     * 得到当前servlet的初始化参数信息。
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取在web.xml中配置的初始化参数
        //获取当前servlet中定义的变量
        String paramVal = this.config.getInitParameter("name");
        resp.getWriter().print(paramVal);
        resp.getWriter().printf("<hr/>");

        //获取所有的初始化参数
        Enumeration<String> enumeration = config.getInitParameterNames();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String value = config.getInitParameter(name);
            resp.getWriter().print(name + "=" + value + "<br/>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
