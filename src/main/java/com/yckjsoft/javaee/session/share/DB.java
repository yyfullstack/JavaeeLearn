package com.yckjsoft.javaee.session.share;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by yong on 2016/10/25 0025.
 * 模拟数据库
 */
public class DB {
    private static Map<String, Book> map = new LinkedHashMap<String, Book>();

    static {
        map.put("1", new Book("1", "javaweb开发"));
        map.put("2", new Book("2", "spring开发"));
        map.put("3", new Book("3", "hibernate开发"));
        map.put("4", new Book("4", "struts开发"));
        map.put("5", new Book("5", "ajax开发"));
    }

    public static Map<String, Book> getAll() {
        return map;
    }
}
