package com.jsptag.example;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yong on 2016/11/2 0002.
 * ForEach迭代标签, 只能遍历list集合
 */
public class ForEachTag extends SimpleTagSupport {
    //存储集合
    private List items;
    //迭代集合时使用的变量
    private String var;

    @Override
    public void doTag() throws JspException, IOException {
        PageContext pageContext = (PageContext) this.getJspContext();
        Iterator it = items.iterator();
        while (it.hasNext()) {
            //得到一个迭代出来的对象
            Object object = it.next();
            //将迭代出来的对象存放到pageContext对象中
            pageContext.setAttribute(var, object);
            //输出标签体中的内容
            this.getJspBody().invoke(null);
        }
    }

    /**
     * Setter for property 'items'.
     *
     * @param items Value to set for property 'items'.
     */
    public void setItems(List items) {
        this.items = items;
    }

    /**
     * Setter for property 'var'.
     *
     * @param var Value to set for property 'var'.
     */
    public void setVar(String var) {
        this.var = var;
    }
}
