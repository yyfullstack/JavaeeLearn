package com.yckjsoft.javaee.customtag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by yong on 2016/11/1 0001.
 * SimpleTagSupport类实现了SimpleTag接口，SampleTagDemo3类继承SimpleTagSupport
 */
public class SimpleTagDemo3 extends SimpleTagSupport {

    /* 简单标签使用这个方法就可以完成所有的业务逻辑
      * @see javax.servlet.jsp.tagext.SimpleTagSupport#doTag()
      * 重写doTag方法，修改标签体里面的内容，将标签体的内容转换成大写
      */
    @Override
    public void doTag() throws JspException, IOException {
        //得到代表jsp标签体的JspFragment
        JspFragment jspFragment = this.getJspBody();
        StringWriter sw = new StringWriter();
        //将标签体的内容写入到sw流中
        jspFragment.invoke(sw);
        //获取sw流缓冲区的内容
        String content = sw.getBuffer().toString();
        content = content.toUpperCase();
        PageContext pageContext = (PageContext) this.getJspContext();
        //将修改后的content输出到浏览器中
        pageContext.getOut().write(content);
    }
}
