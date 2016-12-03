package com.jsptag.example;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by yong on 2016/11/2 0002.
 * foreach标签可以遍历所有集合类型
 */
public class ForEachTag2 extends SimpleTagSupport {
    //存储数据
    private Object items;
    //迭代集合时使用的变量
    private String var;
    //集合，用于存储items中的数据
    private Collection collection;

    @Override
    public void doTag() throws JspException, IOException {
        PageContext pageContext = (PageContext) this.getJspContext();
        Iterator it = collection.iterator();
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
    public void setItems(Object items) {
        if (items instanceof Collection) {
            collection = (Collection) items;
        } else if (items instanceof Map) {
            Map map = (Map) items;
            collection = map.entrySet();
        } else if (items.getClass().isArray()) {
            collection = new ArrayList();
            //获取数组的长度
            int len = Array.getLength(items);
            for (int i = 0; i < len; i++) {
                //获取数组元素
                Object obj = Array.get(items, i);
                collection.add(obj);
            }
        }
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
