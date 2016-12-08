package com.jdbc.utils;


import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by yong on 2016/11/9 0009.
 * 数据库连接工具类 DBCP
 */
public class JdbcUtils_DBCP {
    /**
     * 在java中，编写数据库连接池需实现java.sql.DataSource接口，
     * 每一种数据库连接池都是DataSource接口的实现
     *
     * DBCP连接池就是java.sql.DataSource接口的一个具体实现
     */
    private static DataSource ds = null;

    //在静态代码块中创建数据库连接池
    static {
        //加载dbcpconfig.properties配置文件
        InputStream in = JdbcUtils_DBCP.class.getClassLoader().getResourceAsStream("dbcp_db.properties");
        Properties prop = new Properties();
        try {
            prop.load(in);
            //创建数据源
            ds = BasicDataSourceFactory.createDataSource(prop);
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * Getter for property 'ds'.
     *
     * @return Value for property 'ds'.
     */
    public static DataSource getDs() {
        return ds;
    }

    //从数据源中获取数据库连接
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
