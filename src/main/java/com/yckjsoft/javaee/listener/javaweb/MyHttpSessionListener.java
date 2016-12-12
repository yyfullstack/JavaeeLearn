package com.yckjsoft.javaee.listener.javaweb;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by yong on 2016/11/22 0022.
 * MyHttpSessionListener类实现了HttpSessionListener接口，
 * 因此可以对HttpSession对象的创建和销毁这两个动作进行监听。
 */
public class MyHttpSessionListener implements HttpSessionListener {
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        System.out.println(httpSessionEvent.getSession() + "创建了");
        System.out.println("创建好的httpsession的sessionid" + httpSessionEvent.getSession().getId());

    }

    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        System.out.println(httpSessionEvent.getSession() + "销毁了");
    }
}
