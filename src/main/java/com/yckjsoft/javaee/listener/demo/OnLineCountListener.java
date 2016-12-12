package com.yckjsoft.javaee.listener.demo;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by yong on 2016/12/10 0010.
 * 统计当前在线用户个数
 */
public class OnLineCountListener implements HttpSessionListener {
    public void sessionCreated(HttpSessionEvent se) {
        ServletContext context = se.getSession().getServletContext();
        Integer onLineCount = (Integer) context.getAttribute("onLineCount");
        if (onLineCount == null) {
            context.setAttribute("onLineCount", 1);
        } else {
            onLineCount++;
            context.setAttribute("onLineCount", onLineCount);
        }
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        ServletContext context = se.getSession().getServletContext();
        Integer onLineCount = (Integer) context.getAttribute("onLineCount");
        if (onLineCount == null) {
            context.setAttribute("onLineCount", 1);
        } else {
            onLineCount--;
            context.setAttribute("onLineCount", onLineCount);
        }
    }
}
