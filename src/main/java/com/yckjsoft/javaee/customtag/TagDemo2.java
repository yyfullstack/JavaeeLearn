package com.yckjsoft.javaee.customtag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Created by yong on 2016/11/1 0001.
 * TagSupport类实现了Tag接口，TagDemo2继承TagSupport类
 */
public class TagDemo2 extends TagSupport {
    /**
     * 重写doEndTag方法，控制jsp页面是否执行
     *
     * @return
     * @throws JspException
     * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
     */
    @Override
    public int doEndTag() throws JspException {
        //如果这个方法返回EVAL_PAGE，则执行标签余下的jsp页面，如果返回SKIP_PAGE，则不执行余下的jsp
        //return Tag.SKIP_PAGE;
        return Tag.EVAL_PAGE;

    }
}
