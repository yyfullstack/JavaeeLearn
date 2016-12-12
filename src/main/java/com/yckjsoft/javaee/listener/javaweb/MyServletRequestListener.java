package com.yckjsoft.javaee.listener.javaweb;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * Created by yong on 2016/11/22 0022.
 * MyServletRequestListener类实现了ServletRequestListener接口，
 * 因此可以对ServletRequest对象的创建和销毁这两个动作进行监听。
 */
public class MyServletRequestListener implements ServletRequestListener {

    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        System.out.println(servletRequestEvent.getServletRequest() + "销毁了");
    }

    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        System.out.println(servletRequestEvent.getServletRequest() + "创建了");
    }
}
