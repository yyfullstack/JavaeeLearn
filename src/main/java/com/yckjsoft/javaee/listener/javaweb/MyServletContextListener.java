package com.yckjsoft.javaee.listener.javaweb;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by yong on 2016/11/22 0022.
 * 编写一个MyServletContextListener类，实现ServletContextListener接口，
 * 监听ServletContext对象的创建和销毁
 */
public class MyServletContextListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("ServletContext对象创建");
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("ServletContext对象销毁");
    }
}
