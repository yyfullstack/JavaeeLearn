package com.smart4j.chapter2.util;

import com.smart4j.chapter2.web.filter.ConnectionContext;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringStack;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.rmi.runtime.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by yong on 2016/12/17 0017.
 */
public class DatabaseHelper {
    private static final QueryRunner QUERY_RUNNER = new QueryRunner();

    private static final Logger LOGGER = LoggerFactory.getLogger(PropsUtil.class);

    /**
     * 查询实体
     *
     * @param entityClass
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    public static <T> T queryEntity(Class<T> entityClass, String sql, Object... params) {
        T entity;
        try {
            entity = QUERY_RUNNER.query(JdbcUtilsWithThreadLocal.getConnection(), sql,
                    new BeanHandler<T>(entityClass), params);
        } catch (SQLException e) {
            LOGGER.error("execute query entity failure", e);
            throw new RuntimeException(e);
        } finally {
            //关闭数据库连接
            JdbcUtilsWithThreadLocal.close();
        }
        return entity;
    }

    /**
     * 查询实体列表
     *
     * @param entityClass
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    public static <T> List<T> queryEntityList(Class<T> entityClass, String sql, Object... params) {
        List<T> result;
        try {
            result = QUERY_RUNNER.query(JdbcUtilsWithThreadLocal.getConnection(), sql,
                    new BeanListHandler<T>(entityClass), params);
        } catch (SQLException e) {
            LOGGER.error("execute query list failure", e);
            throw new RuntimeException(e);
        } finally {
            //关闭数据库连接
            JdbcUtilsWithThreadLocal.close();
        }
        return result;
    }

    /**
     * 多表查询
     *
     * @param sql
     * @param params
     * @return
     */
    public static List<Map<String, Object>> executeQuery(String sql, Object... params) {
        List<Map<String, Object>> result;
        try {
            result = QUERY_RUNNER.query(JdbcUtilsWithThreadLocal.getConnection(), sql,
                    new MapListHandler(), params);
        } catch (SQLException e) {
            LOGGER.error("execute query  failure", e);
            throw new RuntimeException(e);
        } finally {
            //关闭数据库连接
            JdbcUtilsWithThreadLocal.close();
        }
        return result;
    }

    public static <T> boolean insertEntity(Class<T> entityClass, Map<String, Object> fieldMap) {
        if (CollectionUtil.isEmpty(fieldMap)) {
            LOGGER.error("can not insert entity: fieldMap is empty");
            return false;
        }
        String sql = "INSERT INTO " + getTableName(entityClass);
        StringBuilder columns = new StringBuilder("(");
        StringBuilder values = new StringBuilder("(");
        for (String fieldName : fieldMap.keySet()) {
            columns.append(fieldName).append(", ");
            values.append("?, ");
        }
        columns.replace(columns.lastIndexOf(", "), columns.length(), ")");
        values.replace(values.lastIndexOf(", "), values.length(), ")");
        sql += columns + " VALUES " + values;

        Object[] params = fieldMap.values().toArray();
        return executeUpdate(sql, params) == 1;
    }


    public static <T> boolean updateEntity(Class<T> entityClass, int id, Map<String, Object> fieldMap) {
        if (CollectionUtil.isEmpty(fieldMap)) {
            LOGGER.error("can not update entity: fieldMap is empty");
            return false;
        }
        String sql = "UPDATE " + getTableName(entityClass) + " SET ";
        StringBuilder columns = new StringBuilder();
        for (String fieldName : fieldMap.keySet()) {
            columns.append(fieldName).append("=?, ");
        }
        sql += columns.substring(0, columns.lastIndexOf(", ")) + " WHERE id=? ";

        System.out.println("update----->" + sql);

        List<Object> paramList = new ArrayList<Object>();
        paramList.addAll(fieldMap.values());
        paramList.add(id);
        Object[] params = paramList.toArray();
        return executeUpdate(sql, params) == 1;
    }

    public static <T> boolean deleteEntity(Class<T> entityClass, int id) {
        String sql = "DELETE FROM " + getTableName(entityClass) + " WHERE id=? ";
        return executeUpdate(sql, id) == 1;
    }


    public static String getTableName(Class<?> entityClass) {
        return entityClass.getSimpleName();
    }

    public static int executeUpdate(String sql, Object... params) {
        int rows = 0;
        try {
            rows = QUERY_RUNNER.update(JdbcUtilsWithThreadLocal.getConnection(), sql, params);
        } catch (SQLException e) {
            LOGGER.error("execute update  failure", e);
            throw new RuntimeException(e);
        } finally {
            //关闭数据库连接
            JdbcUtilsWithThreadLocal.close();
        }
        return rows;
    }
}
