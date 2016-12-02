package com.yckjsoft.javaee.customtag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Created by yong on 2016/11/1 0001.
 * TagSupport类实现了Tag接口，TagDemo1继承TagSupport类
 */
public class TagDemo1 extends TagSupport {
    /**
     * 重写doStartTag方法，控制标签体是否执行
     *
     * @return
     * @throws JspException
     * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
     */
    @Override
    public int doStartTag() throws JspException {
        //如果这个方法返回EVAL_BODY_INCLUDE，则执行标签体，如果返回SKIP_BODY，则不执行标签体
        return Tag.EVAL_BODY_INCLUDE;
        //return Tag.SKIP_BODY;
    }
}
