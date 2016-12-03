package com.jsptag.example;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by yong on 2016/11/2 0002.
 * 开发if标签
 */
public class IFTag extends SimpleTagSupport {
    //if标签的test属性
    private boolean test;

    @Override
    public void doTag() throws JspException, IOException {
        if (test) {
            this.getJspBody().invoke(null);
        }
    }

    /**
     * Setter for property 'test'.
     *
     * @param test Value to set for property 'test'.
     */
    public void setTest(boolean test) {
        this.test = test;
    }
}
