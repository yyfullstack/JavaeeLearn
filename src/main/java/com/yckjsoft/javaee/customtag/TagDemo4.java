package com.yckjsoft.javaee.customtag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.*;
import java.io.IOException;

/**
 * Created by yong on 2016/11/1 0001.
 * BodyTagSupport类实现了BodyTag接口接口，TagDemo4继承 BodyTagSupport类
 */
public class TagDemo4 extends BodyTagSupport {
    //控制doStartTag()方法返回EVAL_BODY_BUFFERED
    @Override
    public int doStartTag() throws JspException {
        return BodyTag.EVAL_BODY_BUFFERED;
    }

    @Override
    public int doEndTag() throws JspException {
        //this.getBodyContent()得到代表标签体的bodyContent对象
        BodyContent bodyContent = this.getBodyContent();
        //拿到标签体
        String content = bodyContent.getString();
        //修改标签体里面的内容，将标签体的内容转换成大写
        String result = content.toUpperCase();

        try {
            //输出修改后的内容
            this.pageContext.getOut().write(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return Tag.EVAL_PAGE;
    }
}
