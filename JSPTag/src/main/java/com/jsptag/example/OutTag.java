package com.jsptag.example;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by yong on 2016/11/2 0002.
 * 开发输出标签
 */
public class OutTag extends SimpleTagSupport {
    // 要输出的内容
    private String content;
    //是否将内容中的html进行转义后输出
    private boolean escapeHtml;

    private String filter(String message) {
        if (message == null) {
            return null;
        }
        char[] content = new char[message.length()];
        message.getChars(0, message.length(), content, 0);
        StringBuilder result = new StringBuilder(content.length + 50);
        for (int i = 0; i < content.length; i++) {
            switch (content[i]) {
                case '<':
                    result.append("&lt;");
                    break;
                case '>':
                    result.append("&gt;");
                    break;
                case '&':
                    result.append("&amp;");
                    break;
                case '"':
                    result.append("&quot;");
                    break;
                default:
                    result.append(content[i]);
            }
        }
        return result.toString();
    }

    @Override
    public void doTag() throws JspException, IOException {
        if (escapeHtml == true) {
            //转义内容中的html代码
            content = filter(content);
        }
        //输出转义后的content
        this.getJspContext().getOut().write(content);
    }

    /**
     * Setter for property 'content'.
     *
     * @param content Value to set for property 'content'.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Setter for property 'escapeHtml'.
     *
     * @param escapeHtml Value to set for property 'escapeHtml'.
     */
    public void setEscapeHtml(boolean escapeHtml) {
        this.escapeHtml = escapeHtml;
    }
}
