package com.yckjsoft.javaee.servlet3.demo.controller;

import com.yckjsoft.javaee.servlet3.demo.WebInitParam;
import com.yckjsoft.javaee.servlet3.demo.WebServlet;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* 
* @ClassName: LoginServlet
* @Description:处理用户登录的Servlet，
* LoginServlet现在就是一个普通的java类，不是一个真正的Servlet
* @author: 孤傲苍狼
* @date: 2014-10-8 上午12:07:58
*
*/
//将开发好的WebServlet注解标注到LoginServlet类上
@WebServlet(
            //Servlet的访问URL
            value="/servlet/LoginServlet",
            //Servlet的访问URL，可以使用数组的方式配置多个访问路径
            urlPatterns={"/yy/LoginServlet","/xdp/LoginServlet"},
            //Servlet的初始化参数
            initParams={
                    @WebInitParam(paramName="gacl",paramValue="孤傲苍狼"),
                    @WebInitParam(paramName="bhsh",paramValue="白虎神皇")
            },
            name="LoginServlet",
            description="处理用户登录的Servlet"
        )
public class LoginServlet {

    public void loginHandle(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        String username = request.getParameter("usename");
        String pwd = request.getParameter("pwd");
        if (username.equals("gacl") && pwd.equals("xdp")) {
            request.getSession().setAttribute("usename", username);
            request.setAttribute("msg", "欢迎您！"+username);
            request.getRequestDispatcher("/demo/servlet3/index.jsp").forward(request, response);
        }else {
            request.setAttribute("msg", "登录失败，请检查用户名和密码是否正确！");
            request.getRequestDispatcher("/demo/servlet3/Login.jsp").forward(request, response);
        }
    }
    
    
    /**
    * @Method: init
    * @Description: Servlet初始化
    * @Anthor:孤傲苍狼
    *
    * @param config
    */ 
    public void init(Map<String, String> initParamMap){
        System.out.println("--LoginServlet初始化--");
        System.out.println(initParamMap.get("gacl"));
        System.out.println(initParamMap.get("bhsh"));
    }
}