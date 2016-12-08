package com.jdbc.demo;


import com.jdbc.utils.JdbcUtilsByPool;
import com.jdbc.utils.JdbcUtils_JNDI;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by yong on 2016/11/9 0009.
 */
public class JdbcPool_DataSourceTest {

    @Test
    public void insert() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtilsByPool.getConnection();
            st = conn.createStatement();
            String sql = "insert into users(id, name, password, email, birthday) values " +
                    "(6, '白虎', '123', 'bbsh@sina.com','1984-09-12')";
            //执行插入操作，executeUpdate方法返回成功的条数
            int num = st.executeUpdate(sql);
            if (num > 0) {
                System.out.println("插入成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtilsByPool.release(conn, st, rs);
        }
    }

    @Test
    public void delete() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtilsByPool.getConnection();
            st = conn.createStatement();
            String sql = "delete from users where id = 4";
            //执行插入操作，executeUpdate方法返回成功的条数
            int num = st.executeUpdate(sql);
            if (num > 0) {
                System.out.println("删除成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //SQL执行完成之后释放相关资源
            JdbcUtilsByPool.release(conn, st, rs);
        }
    }

    @Test
    public void update() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtilsByPool.getConnection();
            st = conn.createStatement();
            String sql = "update users set name='馋懒', email='gacl@sina.com' WHERE id = 4";
            //执行插入操作，executeUpdate方法返回成功的条数
            int num = st.executeUpdate(sql);
            if (num > 0) {
                System.out.println("更新成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //SQL执行完成之后释放相关资源
            JdbcUtilsByPool.release(conn, st, rs);
        }
    }

    @Test
    public void find() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtilsByPool.getConnection();
            st = conn.createStatement();
            String sql = "select * from users WHERE id = 6";
            //执行插入操作，executeUpdate方法返回成功的条数
            rs = st.executeQuery(sql);
            if (rs.next()) {
                System.out.println(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //SQL执行完成之后释放相关资源
            JdbcUtilsByPool.release(conn, st, rs);
        }
    }
}
