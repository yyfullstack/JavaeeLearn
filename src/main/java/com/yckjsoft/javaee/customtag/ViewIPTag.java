package com.yckjsoft.javaee.customtag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;
import java.io.IOException;

/**
 * Created by yong on 2016/11/1 0001.
 */
public class ViewIPTag implements Tag {
    //接收传递进来的PageContext对象
    private PageContext pageContext;


    public void setPageContext(PageContext pageContext) {
        System.out.println("调用setPageContext()方法");
        this.pageContext = pageContext;
    }

    public void setParent(Tag tag) {

    }

    public Tag getParent() {
        return null;
    }

    public int doStartTag() throws JspException {
        System.out.println("调用doStartTag()方法");
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        JspWriter out = pageContext.getOut();
        String ip = request.getRemoteAddr();
        try {
            out.write(ip);
        } catch (IOException e) {
            //捕获IOException异常后继续抛出
            throw new RuntimeException(e);
        }
        return 0;
    }

    public int doEndTag() throws JspException {
        System.out.println("调用doEndTag()方法");
        return 0;
    }

    public void release() {
        System.out.println("调用release()方法");
    }
}
