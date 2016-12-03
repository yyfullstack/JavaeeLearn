package com.jsptag.example;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by yong on 2016/11/2 0002.
 * html转义标签
 */
public class HTMLEscapeTag extends SimpleTagSupport {
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
        StringWriter sw = new StringWriter();
        //将标签体中的内容先输出到StringWriter流
        this.getJspBody().invoke(sw);
        //得到标签体中的内容
        String content = sw.getBuffer().toString();
        //转义标签体中的html代码
        content = filter(content);
        //输出转义后的content
        this.getJspContext().getOut().write(content);
    }


}
