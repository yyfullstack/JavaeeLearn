package com.jdbc.demo;


import com.jdbc.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yong on 2016/11/9 0009.
 */
public class JdbcBatchHandleByPreparedStatement {

    @Test
    public void insert() {
        long starttime = System.currentTimeMillis();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "insert into testbatch(id, name) values(?,?)";
            st = conn.prepareStatement(sql);
            for (int i = 1; i < 100; i++) {
                st.setInt(1, i);
                st.setString(2, "aaa" + i);
                st.addBatch();
               /* if (i % 1000 == 0) {
                    //执行批处理SQL语句
                    st.executeBatch();
                    //清除批处理命令
                    st.clearBatch();
                }*/
            }
            st.executeBatch();
            //清除批处理命令
            st.clearBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
        long endtime = System.currentTimeMillis();
        System.out.println("程序花费时间：" + (endtime - starttime) / 1000 + "秒！！");
    }

}
