package com.jdbc.utils;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by yong on 2016/11/9 0009.
 * 数据库连接工具类 DBCP
 */
public class JdbcUtils_JNDI {
    private static DataSource ds = null;

    static {
        try {
            //初始化JNDI
            Context initCtx = new InitialContext();
            //得到JNDI容器
            Context envCtx = (Context) initCtx.lookup("java:/comp/env");
            //从JNDI容器中检索name为jdbc/JdbcStudyDB的数据源
            ds = (DataSource) envCtx.lookup("jdbc/JdbcStudyDB");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
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
