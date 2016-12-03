package com.jsptag.example;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by yong on 2016/11/2 0002.
 * when标签和otherwise标签的父标签
 */
public class ChooseTag extends SimpleTagSupport {
    /**
     * 定义一个boolean类型的属性，该属性用于标识该标签下的某一个子标签是否已经执行过了，
     * 如果该标签下的某一个子标签已经执行过了，就将该属性设置为true
     */
    private boolean isExecute;

    @Override
    public void doTag() throws JspException, IOException {
        //输出标签体中的内容
        this.getJspBody().invoke(null);
    }

    /**
     * Setter for property 'execute'.
     *
     * @param execute Value to set for property 'execute'.
     */
    public void setExecute(boolean execute) {
        isExecute = execute;
    }

    /**
     * Getter for property 'execute'.
     *
     * @return Value for property 'execute'.
     */
    public boolean isExecute() {
        return isExecute;
    }
}
