package com.yckjsoft.javaee.servlet3.virtual;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yong on 2016/12/10 0010.
 * WebInitParam
 *
 * @Description: 定义Servlet的初始化参数注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface WebInitParam {
    //参数名
    String paramName() default "";

    //参数的值
    String paramValue() default "";
}
