package com.jdbc.utils;


import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Created by yong on 2016/11/9 0009.
 * 编写一个JdbcUtils工具类，用于连接数据库，获取数据库连接和释放数据库连接
 */
public class JdbcUtils {
    private static String driver = null;
    private static String url = null;
    private static String username = null;
    private static String password = null;

    static {
        InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");
        Properties prop = new Properties();
        try {
            prop.load(in);
            //mysql获取数据库连接信息
            driver = prop.getProperty("driver");
            url = prop.getProperty("url");
            username = prop.getProperty("username");
            password = prop.getProperty("password");
            //oracle获取数据库连接信息
            //获取数据库连接信息
           /* driver = prop.getProperty("oracle_driver");
            url = prop.getProperty("oracle_url");
            username = prop.getProperty("oracle_username");
            password = prop.getProperty("oracle_password");*/
            //获取数据库连接驱动
            Class.forName(driver);
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
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
