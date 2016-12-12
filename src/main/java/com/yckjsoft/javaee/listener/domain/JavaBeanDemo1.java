package com.yckjsoft.javaee.listener.domain;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * Created by yong on 2016/11/22 0022.
 * 实现了HttpSessionBindingListener接口的 JavaBean对象可以感知自己被绑定到 Session中和从Session中删除的事件
 * 当对象被绑定到 HttpSession 对象中时，web 服务器调用该对象的  void valueBound(HttpSessionBindingEvent event) 方法
 * 当对象从 HttpSession 对象中解除绑定时，web 服务器调用该对象的 void valueUnbound(HttpSessionBindingEvent event)方法
 */
public class JavaBeanDemo1 implements HttpSessionBindingListener {
    private String name;

    public JavaBeanDemo1(String name) {
        this.name = name;
    }

    /**
     * Getter for property 'name'.
     *
     * @return Value for property 'name'.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for property 'name'.
     *
     * @param name Value to set for property 'name'.
     */
    public void setName(String name) {
        this.name = name;
    }

    public void valueBound(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println(name + "被加到session中了");
    }

    public void valueUnbound(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println(name + "被session踢出来了");
    }
}
