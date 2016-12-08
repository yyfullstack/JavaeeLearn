package com.jdbc.jdbc_framework;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yong on 2016/11/19 0019.
 * 将结果集转换成bean对象的list集合的处理器
 */
public class BeanListHandler implements ResultSetHandler {
    private Class<?> clazz;

    public BeanListHandler(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Object handler(ResultSet rs) {
        try {
            List<Object> list = new ArrayList<Object>();
            while (rs.next()) {
                Object bean = clazz.newInstance();

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
                list.add(bean);
            }

            return list.size() > 0 ? list : null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
