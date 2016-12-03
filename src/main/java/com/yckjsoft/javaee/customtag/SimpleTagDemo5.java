package com.yckjsoft.javaee.customtag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by yong on 2016/11/1 0001.
 * SimpleTagSupport类实现了SimpleTag接口，SampleTagDemo5类继承SimpleTagSupport
 * 通过标签的属性控制标签体的执行次数
 */
public class SimpleTagDemo5 extends SimpleTagSupport {

    /*　　如果标签的属性值是8种基本数据类型，那么在JSP页面在传递字符串时，JSP引擎会自动转换成相应的类型，
    但如果标签的属性值是复合数据类型，那么JSP引擎是无法自动转换的*/
    //定义标签的属性
    private int count;

    /**
     * Setter for property 'count'.
     *
     * @param count Value to set for property 'count'.
     */
    public void setCount(int count) {
        this.count = count;
    }

    /* 简单标签使用这个方法就可以完成所有的业务逻辑
      * @see javax.servlet.jsp.tagext.SimpleTagSupport#doTag()
      * 重写doTag方法，通过标签的属性控制标签体的执行次数
      */
    @Override
    public void doTag() throws JspException, IOException {
        for (int i = 0; i < count; i++) {
            this.getJspBody().invoke(null);
        }
    }
}
