package com.jdbc.jdbc_framework;


import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Created by yong on 2016/11/9 0009.
 * 封装通用的update方法和qurey方法
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
            //获取数据库连接信息
            driver = prop.getProperty("driver");
            url = prop.getProperty("url");
            username = prop.getProperty("username");
            password = prop.getProperty("password");
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

    //释放资源，
    // 要释放的资源包括Connection数据库连接对象，负责执行SQL命令的Statement对象，存储查询结果的ResultSet对象
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

    /**
     * @param sql    要执行的SQL
     * @param params 执行SQL时使用的参数
     * @throws SQLException
     * @Method: update
     * @Description: 万能更新
     * 所有实体的CUD操作代码基本相同，仅仅发送给数据库的SQL语句不同而已，
     * 因此可以把CUD操作的所有相同代码抽取到工具类的一个update方法中，并定义参数接收变化的SQL语句
     * @Anthor:孤傲苍狼
     */
    public static void update(String sql, Object[] params) throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            st = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                st.setObject(i + 1, params[i]);
            }
            st.executeUpdate();
        } finally {
            release(conn, st, rs);
        }
    }

    /**
     * @param sql    要执行的SQL
     * @param params 执行SQL时使用的参数
     * @param rsh    查询返回的结果集处理器
     * @return
     * @throws SQLException
     * @Method: query
     * @Description:万能查询 实体的R操作，除SQL语句不同之外，根据操作的实体不同，对ResultSet的映射也各不相同，
     * 因此可义一个query方法，除以参数形式接收变化的SQL语句外，可以使用策略模式由qurey方法的调用者决定如何把ResultSet中的数据映射到实体对象中。
     * @Anthor:孤傲苍狼
     */
    public static Object query(String sql, Object[] params, ResultSetHandler rsh) throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        Object result = null;
        try {
            conn = getConnection();
            st = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                st.setObject(i + 1, params[i]);
            }
            rs = st.executeQuery();
            /**
             * 对于查询返回的结果集处理使用到了策略模式，
             * 在设计query方法时，query方法事先是无法知道用户对返回的查询结果集如何进行处理的，即不知道结果集的处理策略，
             * 那么这个结果集的处理策略就让用户自己提供，query方法内部就调用用户提交的结果集处理策略进行处理
             * 为了能够让用户提供结果集的处理策略，需要对用户暴露出一个结果集处理接口ResultSetHandler
             * 用户只要实现了ResultSetHandler接口，那么query方法内部就知道用户要如何处理结果集了
             */
            result = rsh.handler(rs);
        } finally {
            release(conn, st, rs);
        }
        return result;
    }
}
