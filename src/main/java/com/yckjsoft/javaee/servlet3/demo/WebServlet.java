package com.yckjsoft.javaee.servlet3.demo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yong on 2016/12/10 0010.
 * 自定义WebServlet注解，模拟Servlet3.0的WebServlet注解
 *
 * @Target 注解的属性值表明了 @WebServlet注解只能用于类或接口定义声明的前面，
 * @WebServlet注解有一个必填的属性 value 。
 * 调用方式为： @WebServlet(value="/xxxx") ，
 * 因语法规定如果属性名为 value 且只填 value属性值时，可以省略 value属性名，即也可以写作：@WebServlet("/xxxx")
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface WebServlet {
    //Servlet的访问URL
    // 一个必填的属性 value 。
    String value();

    //Servlet的访问URL
    String[] urlPatterns() default {""};

    //Servlet的描述
    String description() default "";

    //Servlet的显示名称
    String displayName() default "";

    //Servlet的名称
    String name() default "";

    //Servlet的init参数
    WebInitParam[] initParams() default {};
}
