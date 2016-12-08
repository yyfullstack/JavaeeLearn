package com.jdbc.demo;


import com.jdbc.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by yong on 2016/11/9 0009.
 */
public class JdbcCURDByStatement {

    @Test
    //　使用executeUpdate(String sql)方法完成数据添加操作
    public void insert() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            //获取一个数据库连接
            conn = JdbcUtils.getConnection();
            //通过conn对象获取负责执行SQL命令的Statement对象
            st = conn.createStatement();
            //要执行的SQL命令
            String sql = "insert into users(id, name, password, email, birthday) values " +
                    "(4, '白虎', '123', 'bbsh@sina.com','1984-09-12')";
            //执行插入操作，executeUpdate方法返回成功的条数
            int num = st.executeUpdate(sql);
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
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            st = conn.createStatement();
            String sql = "delete from users where id = 4";
            //执行插入操作，executeUpdate方法返回成功的条数
            int num = st.executeUpdate(sql);
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
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            st = conn.createStatement();
            String sql = "update users set name='馋懒', email='gacl@sina.com' WHERE id = 4";
            //执行插入操作，executeUpdate方法返回成功的条数
            int num = st.executeUpdate(sql);
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
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            st = conn.createStatement();
            String sql = "select * from users WHERE id = 4";
            //执行插入操作，executeUpdate方法返回成功的条数
            rs = st.executeQuery(sql);
            if (rs.next()) {
                System.out.println(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //SQL执行完成之后释放相关资源
            JdbcUtils.release(conn, st, rs);
        }
    }
}
