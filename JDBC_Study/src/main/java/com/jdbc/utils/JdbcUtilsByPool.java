package com.jdbc.utils;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by yong on 2016/11/9 0009.
 */
public class JdbcUtilsByPool {

    //数据库连接池
    private static JdbcPool pool = new JdbcPool();

    //从数据库连接池中获取数据库连接对象
    public static Connection getConnection() throws SQLException {
        return pool.getConnection();
    }

    public static void release(Connection conn, Statement st, ResultSet rs) {
        if (rs != null) {
            try {
                //关闭存储查询结果的ResultSet对象
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rs = null;
        }
        if (st != null) {
            try {
                //关闭负责执行SQL命令的Statement对象
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                //关闭Connection数据库连接对象
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
