package com.ycmvc.util;

import java.util.HashMap;
import java.util.Map;

/**
* @ClassName: RequestMapingMap
* @Description: 存储方法的访问路径
*
*/ 
public class RequestMapingMap {

    /**
    * @Field: requesetMap
    *          用于存储方法的访问路径
    */ 
    private static Map<String, Class<?>> requesetMap = new HashMap<String, Class<?>>();
    
    public static Class<?> getClassName(String path) {
        return requesetMap.get(path);
    }

    public static void put(String path, Class<?> className) {
        requesetMap.put(path, className);
    }

    public static Map<String, Class<?>> getRequesetMap() {
        return requesetMap;
    }
}