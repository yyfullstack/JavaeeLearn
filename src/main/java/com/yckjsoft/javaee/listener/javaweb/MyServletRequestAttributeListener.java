package com.yckjsoft.javaee.listener.javaweb;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import java.text.MessageFormat;

/**
 * Created by yong on 2016/11/22 0022.
 * HttpServletRequest域对象中属性的变更的事件监听器
 */
public class MyServletRequestAttributeListener implements ServletRequestAttributeListener {
    public void attributeAdded(ServletRequestAttributeEvent se) {
        String message = MessageFormat.format("ServletRequest对象中添加了属性：{0}，属性值为：{1}",
                se.getName(),
                se.getValue());
        System.out.println(message);
    }

    public void attributeRemoved(ServletRequestAttributeEvent se) {
        String message = MessageFormat.format("ServletRequest对象中删除属性：{0}，属性值为：{1}",
                se.getName(),
                se.getValue());
        System.out.println(message);
    }

    public void attributeReplaced(ServletRequestAttributeEvent se) {
        String message = MessageFormat.format("ServletRequest对象中替换属性：{0}，属性值为：{1}",
                se.getName(),
                //getValue获取的是替换前的属性值
                se.getValue());
        System.out.println(message);
    }
}
