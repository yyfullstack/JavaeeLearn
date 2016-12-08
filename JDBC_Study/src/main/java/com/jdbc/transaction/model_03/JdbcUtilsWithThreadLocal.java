package com.jdbc.transaction.model_03;


import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by yong on 2016/11/9 0009.
 * 数据库连接工具类
 */
public class JdbcUtilsWithThreadLocal {
    private static ComboPooledDataSource ds = null;

    //使用ThreadLocal存储当前线程中的Connection对象
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

    //在静态代码块中创建数据库连接池
    static {
        InputStream in = JdbcUtilsWithThreadLocal.class.getClassLoader().getResourceAsStream("c3p0.properties");
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
     * 获取数据源
     *
     * @return Value for property 'ds'.
     */
    public static ComboPooledDataSource getDs() {
        return ds;
    }

    //从数据源中获取数据库连接
    public static Connection getConnection() throws SQLException {
        //从当前线程中获取connection
        Connection conn = threadLocal.get();
        if (conn == null) {
            //从数据源中获取数据库连接
            conn = getDs().getConnection();
            //将conn绑定到当前线程
            threadLocal.set(conn);
        }
        return ds.getConnection();
    }

    //开启事务
    public static void startTransaction() {
        try {
            Connection conn = threadLocal.get();
            if (conn == null) {
                conn = getConnection();
                //把 conn绑定到当前线程上
                threadLocal.set(conn);
            }
            //开启事务
            conn.setAutoCommit(false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //回滚事务
    public static void rollback() {
        try {
            //从当前线程中获取Connection
            Connection conn = threadLocal.get();
            if (conn != null) {
                //回滚事务
                conn.rollback();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //提交事务
    public static void commit() {
        try {
            //从当前线程中获取Connection
            Connection conn = threadLocal.get();
            if (conn != null) {
                conn.commit();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //关闭数据库连接(注意，并不是真的关闭，而是把连接还给数据库连接池)
    public static void close() {
        try {
            //从当前线程中获取Connection
            Connection conn = threadLocal.get();
            if (conn != null) {
                conn.close();
                //解除当前线程上绑定conn
                threadLocal.remove();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
