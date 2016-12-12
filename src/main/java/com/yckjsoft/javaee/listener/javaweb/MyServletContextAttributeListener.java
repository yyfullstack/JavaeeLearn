package com.yckjsoft.javaee.listener.javaweb;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import java.text.MessageFormat;

/**
 * Created by yong on 2016/11/22 0022.
 * ServletContext域对象中属性的变更的事件监听器
 */
public class MyServletContextAttributeListener implements ServletContextAttributeListener {

    public void attributeAdded(ServletContextAttributeEvent servletContextAttributeEvent) {
        String message = MessageFormat.format("ServletContext对象中添加了属性：{0}，属性值为：{1}",
                servletContextAttributeEvent.getName(),
                servletContextAttributeEvent.getValue());
        System.out.println(message);
    }

    public void attributeRemoved(ServletContextAttributeEvent servletContextAttributeEvent) {
        String message = MessageFormat.format("ServletContext对象中删除属性：{0}，属性值为：{1}",
                servletContextAttributeEvent.getName(),
                servletContextAttributeEvent.getValue());
        System.out.println(message);
    }

    public void attributeReplaced(ServletContextAttributeEvent servletContextAttributeEvent) {
        String message = MessageFormat.format("ServletContext对象中替换属性：{0}，属性值为：{1}",
                servletContextAttributeEvent.getName(),
                //getValue获取的是替换前的属性值
                servletContextAttributeEvent.getValue());
        System.out.println(message);
    }
}
