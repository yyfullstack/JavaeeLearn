package com.jdbc.jdbc_framework;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * Created by yong on 2016/11/19 0019.
 * 将结果集转换成bean对象的处理器
 */
public class BeanHandler implements ResultSetHandler {
    private Class<?> clazz;

    public BeanHandler(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Object handler(ResultSet rs) {
        try {
            if (!rs.next()) {
                return null;
            }
            Object bean = clazz.newInstance();
            //得到结果集元数据
            ResultSetMetaData metaData = rs.getMetaData();
            //得到结果集中有几列数据
            int columnCount = metaData.getColumnCount();
            for (int i = 0; i < columnCount; i++) {
                //得到每列的列名
                String columnName = metaData.getColumnName(i + 1);
                Object columnData = rs.getObject(i + 1);
                //反射出类上列名对应的属性
                Field f = clazz.getDeclaredField(columnName);
                f.setAccessible(true);
                f.set(bean, columnData);
            }
            return bean;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
