package com.jdbc.jdbc_framework;

import java.sql.ResultSet;

/**
 * Created by yong on 2016/11/19 0019.
 * @Description:结果集处理器接口
 */
public interface ResultSetHandler {
    /**
     * 结果集处理方法
     * @param rs 查询结果集
     * @return
     */
    Object handler(ResultSet rs);
}
