package com.jdbc.demo;


import com.jdbc.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by yong on 2016/11/9 0009.
 */
public class JdbcCURDByPreparedStatement {

    @Test
    public void insert() {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            //获取一个数据库连接
            conn = JdbcUtils.getConnection();
            //要执行的SQL命令，SQL中的参数使用?作为占位符
            String sql = "insert into users(id, name, password, email, birthday) values " +
                    "(?, ?, ?, ?,?)";
            //通过conn对象获取负责执行SQL命令的prepareStatement对象
            st = conn.prepareStatement(sql);
            //为SQL语句中的参数赋值，注意，索引是从1开始的
            /**
             SQL语句中各个字段的类型如下：
             +----------+-------------+
             | Field    | Type        |
             +----------+-------------+
             | id       | int(11)     |
             | name     | varchar(40) |
             | password | varchar(40) |
             | email    | varchar(60) |
             | birthday | date        |
             +----------+-------------+
             */
            st.setInt(1, 5);
            st.setString(2, "七公");
            st.setString(3, "123");
            st.setString(4, "qigon@sina.com");
            //birthday是date类型
            st.setDate(5, new java.sql.Date(new Date().getTime()));
            //执行插入操作，executeUpdate方法返回成功的条数
            int num = st.executeUpdate();
            if (num > 0) {
                System.out.println("插入成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
    }

    @Test
    public void insertNew() {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();
            String sql = "insert into users(name, password, email, birthday) values(?, ?, ?, ?) ";
            st = conn.prepareStatement(sql);
            //为SQL语句中的参数赋值，注意，索引是从1开始的
            /**
             SQL语句中各个字段的类型如下：
             +----------+-------------+
             | Field    | Type        |
             +----------+-------------+
             | id       | int(11)     |
             | name     | varchar(40) |
             | password | varchar(40) |
             | email    | varchar(60) |
             | birthday | date        |
             +----------+-------------+
             */
            st.setString(1, "七公");
            st.setString(2, "123");
            st.setString(3, "qigon@sina.com");
            //birthday是date类型
            st.setObject(4, "1984-09-12");

            //执行插入操作，executeUpdate方法返回成功的条数
            int num = st.executeUpdate();
            if (num > 0) {
                System.out.println("插入成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
    }

    @Test
    public void delete() {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "delete from users where id = ?";
            st = conn.prepareStatement(sql);
            st.setInt(1, 5);
            //执行插入操作，executeUpdate方法返回成功的条数
            int num = st.executeUpdate();
            if (num > 0) {
                System.out.println("删除成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //SQL执行完成之后释放相关资源
            JdbcUtils.release(conn, st, rs);
        }
    }

    @Test
    public void update() {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "update users set name=?, email=? WHERE id = ?";
            st = conn.prepareStatement(sql);
            st.setString(1, "朝凤");
            st.setString(2, "caofeng@sina.com");
            st.setInt(3, 5);
            //执行插入操作，executeUpdate方法返回成功的条数
            int num = st.executeUpdate();
            if (num > 0) {
                System.out.println("更新成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //SQL执行完成之后释放相关资源
            JdbcUtils.release(conn, st, rs);
        }
    }

    @Test
    public void find() {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();
            String sql = "select * from users WHERE id = ?";
            st = conn.prepareStatement(sql);
            st.setInt(1, 5);
            //执行插入操作，executeUpdate方法返回成功的条数
            rs = st.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //SQL执行完成之后释放相关资源
            JdbcUtils.release(conn, st, rs);
        }
    }
}
