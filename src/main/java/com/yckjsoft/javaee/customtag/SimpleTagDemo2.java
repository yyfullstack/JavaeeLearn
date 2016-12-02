package com.yckjsoft.javaee.customtag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by yong on 2016/11/1 0001.
 * SimpleTagSupport类实现了SimpleTag接口，SampleTagDemo1类继承SimpleTagSupport
 */
public class SimpleTagDemo2 extends SimpleTagSupport {

    /* 简单标签使用这个方法就可以完成所有的业务逻辑
      * @see javax.servlet.jsp.tagext.SimpleTagSupport#doTag()
      * 重写doTag方法，控制标签执行5次
      */
    @Override
    public void doTag() throws JspException, IOException {
        //得到代表jsp标签体的JspFragment
        JspFragment jspFragment = this.getJspBody();
        for (int i = 0; i < 5; i++) {
            // 将标签体的内容输出到浏览器
            jspFragment.invoke(null);
        }
    }
}
