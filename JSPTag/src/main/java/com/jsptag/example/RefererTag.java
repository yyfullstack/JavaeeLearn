package com.jsptag.example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.SkipPageException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by yong on 2016/11/2 0002.
 * 防盗链标签RefererTag
 */
public class RefererTag extends SimpleTagSupport {
    //网站域名
    private String site;
    //要跳转的页面
    private String page;

    @Override
    public void doTag() throws JspException, IOException {
        //获取jsp页面的pagecontext对象
        PageContext pageContext = (PageContext) this.getJspContext();
        //通过pageContext对象来获取httpservletrequest对象
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        //获取请求的来源
        String referer = request.getHeader("referer");
        //如果来路是null或者来路不是来自我们自己的site，那么就将请求重定向到page页面
        if (referer == null || !referer.startsWith(site)) {
            HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();
            String webRoot = request.getContextPath();

            if (page.startsWith(webRoot)) {
                //重定向到page页面
                response.sendRedirect(page);
            } else {
                //重定向到page页面
                response.sendRedirect(webRoot + page);
            }
            //重定向后，控制保护的页面不要执行
            throw new SkipPageException();
        }
    }

    /**
     * Setter for property 'site'.
     *
     * @param site Value to set for property 'site'.
     */
    public void setSite(String site) {
        this.site = site;
    }

    /**
     * Setter for property 'page'.
     *
     * @param page Value to set for property 'page'.
     */
    public void setPage(String page) {
        this.page = page;
    }
}
