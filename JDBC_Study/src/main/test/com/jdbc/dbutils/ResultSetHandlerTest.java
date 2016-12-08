package com.jdbc.dbutils;

import com.jdbc.utils.JdbcUtils_C3P0;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by yong on 2016/11/21 0021.
 * 测试dbutils各种类型的处理器
 */
public class ResultSetHandlerTest {

    private QueryRunner qr = new QueryRunner(JdbcUtils_C3P0.getDs());

    @Test
    public void testArrayHandler() throws SQLException {
        String sql = "select * from users";
        Object[] result = qr.query(sql, new ArrayHandler());
        System.out.println(Arrays.asList(result));
    }

    @Test
    public void testArrayListHandler() throws SQLException {
        String sql = "select * from users";
        List<Object[]> list = qr.query(sql, new ArrayListHandler());
        for (Object[] o : list) {
            System.out.println(Arrays.asList(o));
        }
    }

    @Test
    public void testColumnListHandler() throws SQLException {
        String sql = "select * from users";
        List list = (List) qr.query(sql, new ColumnListHandler("id"));
        System.out.println(list);
    }

    @Test
    public void testKeyedHandler() throws SQLException {
        String sql = "select * from users";
        Map<Integer, Map> map = (Map<Integer, Map>) qr.query(sql, new KeyedHandler("id"));
        for (Map.Entry<Integer, Map> me : map.entrySet()) {
            int id = me.getKey();
            Map<String, Object> innerMap = me.getValue();
            for (Map.Entry<String, Object> innerMe : innerMap.entrySet()) {
                String columnName = innerMe.getKey();
                Object value = innerMe.getValue();
                System.out.println(columnName + " = " + value);

            }
            System.out.println("------------------------------");
        }
    }

    @Test
    public void testMapHandler() throws SQLException {
        String sql = "select * from users";
        Map<String, Object> map = qr.query(sql, new MapHandler());
        for (Map.Entry<String, Object> me : map.entrySet()) {
            String columnName = me.getKey();
            Object value = me.getValue();
            System.out.println(columnName + " = " + value);
        }
    }

    @Test
    public void testMapListHandler() throws SQLException {
        String sql = "select * from users";
        List<Map<String, Object>> list = qr.query(sql, new MapListHandler());
        for (Map<String, Object> map : list) {
            for (Map.Entry<String, Object> me : map.entrySet()) {
                String columnName = me.getKey();
                Object value = me.getValue();
                System.out.println(columnName + " = " + value);
            }
            System.out.println("------------------------------");
        }
    }

    @Test
    public void testScalarHandler() throws SQLException {
        String sql = "select count(*) from users";
        int count = ((Long) qr.query(sql, new ScalarHandler(1))).intValue();
        System.out.println(count);
    }
}
