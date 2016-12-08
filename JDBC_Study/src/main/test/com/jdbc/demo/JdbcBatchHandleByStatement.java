package com.jdbc.demo;


import com.jdbc.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by yong on 2016/11/9 0009.
 * 使用Statement实现JDBC批处理操作
 */
public class JdbcBatchHandleByStatement {

    @Test
    public void insert() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            st = conn.createStatement();
            String sql1 = "insert into testbatch(id, name) values(1, 'aaa')";
            String sql2 = "insert into testbatch(id, name) values(2, 'bbb')";
            String sql3 = "insert into testbatch(id, name) values(3, 'ccc')";
            String sql4 = "insert into testbatch(id, name) values(4, 'ddd')";
            String sql5 = "update testbatch set name='jack' where id = 1";
            String sql6 = "insert into testbatch(id, name) values(5, 'eee')";
            String sql7 = "delete from testbatch where id= 2";
            //添加要批量执行的SQL
            st.addBatch(sql1);
            st.addBatch(sql2);
            st.addBatch(sql3);
            st.addBatch(sql4);
            st.addBatch(sql5);
            st.addBatch(sql6);
            st.addBatch(sql7);
            //执行批处理SQL语句
            st.executeBatch();
            //清除批处理命令
            st.clearBatch();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
    }

}
