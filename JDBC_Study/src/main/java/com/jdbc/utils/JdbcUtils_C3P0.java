package com.jdbc.utils;


import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by yong on 2016/11/9 0009.
 * 数据库连接工具类 C3P0
 */
public class JdbcUtils_C3P0 {
    private static ComboPooledDataSource ds = null;

    static {
        InputStream in = JdbcUtils_C3P0.class.getClassLoader().getResourceAsStream("c3p0_db.properties");
        Properties prop = new Properties();
        try {
            prop.load(in);
            //使用C3P0的命名配置来创建数据源
            ds = new ComboPooledDataSource();
            ds.setDriverClass(prop.getProperty("driverClass"));
            ds.setJdbcUrl(prop.getProperty("jdbcUrl"));
            ds.setUser(prop.getProperty("user"));
            ds.setPassword(prop.getProperty("password"));

            ds.setMinPoolSize(Integer.parseInt(prop.getProperty("c3p0.minPoolSize")));
            ds.setMaxPoolSize(Integer.parseInt(prop.getProperty("c3p0.maxPoolSize")));

            ds.setAcquireIncrement(Integer.parseInt(prop.getProperty("c3p0.acquireIncrement")));
            ds.setAcquireRetryAttempts(Integer.parseInt(prop.getProperty("c3p0.acquireRetryAttempts")));
            ds.setAcquireRetryDelay(Integer.parseInt(prop.getProperty("c3p0.acquireRetryDelay")));

            ds.setAutoCommitOnClose(Boolean.parseBoolean(prop.getProperty("c3p0.autoCommitOnClose")));
            ds.setCheckoutTimeout(Integer.parseInt(prop.getProperty("c3p0.checkoutTimeout")));
            ds.setIdleConnectionTestPeriod(Integer.parseInt(prop.getProperty("c3p0.idleConnectionTestPeriod")));

            ds.setMaxIdleTime(Integer.parseInt(prop.getProperty("c3p0.maxIdleTime")));

            ds.setTestConnectionOnCheckin(Boolean.parseBoolean(prop.getProperty("c3p0.testConnectionOnCheckin")));
            ds.setMaxStatements(Integer.parseInt(prop.getProperty("c3p0.maxStatements")));
            ds.setMaxStatementsPerConnection(Integer.parseInt(prop.getProperty("c3p0.maxStatementsPerConnection")));
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * Getter for property 'ds'.
     *
     * @return Value for property 'ds'.
     */
    public static ComboPooledDataSource getDs() {
        return ds;
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
