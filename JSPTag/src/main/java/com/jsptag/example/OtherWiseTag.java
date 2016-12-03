package com.jsptag.example;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by yong on 2016/11/2 0002.
 * otherwise标签
 */
public class OtherWiseTag extends SimpleTagSupport {


    @Override
    public void doTag() throws JspException, IOException {
        //获取标签的父标签
        ChooseTag parentTag = (ChooseTag) this.getParent();
        if (parentTag.isExecute() == false) {
            //输出标签体中的内容
            this.getJspBody().invoke(null);
            //将父标签的isExecute属性设置为true，告诉父标签，我(otherwise标签)已经执行过了
            parentTag.setExecute(true);
        }
    }

}
