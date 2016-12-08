package com.jdbc.demo;


import com.jdbc.utils.JdbcUtils;

import oracle.sql.CLOB;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yong on 2016/11/9 0009.
 * Oracle中字符型大型对象（Character Large Object）数据处理
 */
public class OracleJdbcOperaClob {

    @Test
    public void clobInsert() throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        conn = JdbcUtils.getConnection();
        boolean defaultCommit = conn.getAutoCommit();
        //开启事务，设定不自动提交
        conn.setAutoCommit(false);
        try {
            //插入一个空的CLOB对象
            String sql = "insert into TEST_CLOB values (?， EMPTY_CLOB())";
            st = conn.prepareStatement(sql);
            st.setInt(1, 2);
            st.executeUpdate();

            // 查询此CLOB对象并锁定
            sql = "SELECT CLOBCOL FROM TEST_CLOB WHERE ID=? FOR UPDATE";
            st = conn.prepareStatement(sql);
            st.setInt(1, 2);
            rs = st.executeQuery();

            if (rs.next()) {
                //取出此CLOB对象
                CLOB clob = (CLOB) rs.getClob("CLOBCOL");
                ///向CLOB对象中写入数据
                BufferedWriter out = new BufferedWriter(clob.setCharacterStream(0L));
                //这种方式获取的路径，其中的空格会被使用“%20”代替
                String path = OracleJdbcOperaClob.class.getClassLoader().getResource("data.txt").getPath();
                //将“%20”替换回空格
                path = path.replaceAll("%20", " ");
                BufferedReader in = new BufferedReader(new FileReader(path));
                int c;
                while ((c = in.read()) != -1) {
                    out.write(c);
                }
                in.close();
                out.close();
            }
            //正式提交
            conn.commit();
            System.out.println("插入成功");
        } catch (Exception e) {
            //出错回滚
            conn.rollback();
            e.printStackTrace();
        } finally {
            //恢复原提交状态
            conn.setAutoCommit(defaultCommit);
            JdbcUtils.release(conn, st, rs);
        }
    }

     @Test
    public void clobRead() throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        conn = JdbcUtils.getConnection();
        boolean defaultCommit = conn.getAutoCommit();
        conn.setAutoCommit(false);
        try {
            String sql = "select * from TEST_CLOB WHERE ID = ?";
            st = conn.prepareStatement(sql);
            st.setInt(1, 2);
            rs = st.executeQuery();
            if (rs.next()) {
                //获取CLOB对象
                CLOB clob = (CLOB) rs.getClob("CLOBCOL");
                //以字符形式输出
                BufferedReader in = new BufferedReader(clob.getCharacterStream());
                BufferedWriter out = new BufferedWriter(new FileWriter("D:\\data.txt"));
                int c;
                while ((c = in.read()) != -1) {
                    out.write(c);
                }
                in.close();
                out.close();
            }
            //提交事务
            conn.commit();
        } catch (Exception e) {
            //出错回滚
            conn.rollback();
            e.printStackTrace();
        } finally {
            //恢复原提交状态
            conn.setAutoCommit(defaultCommit);
            //SQL执行完成之后释放相关资源
            JdbcUtils.release(conn, st, rs);
        }
    }

    @Test
    public void clobModify() throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        conn = JdbcUtils.getConnection();
        boolean defaultCommit = conn.getAutoCommit();
        //开启事务，设定不自动提交
        conn.setAutoCommit(false);
        try {
             /* 查询CLOB对象并锁定 */
            String sql = "SELECT CLOBCOL FROM TEST_CLOB WHERE ID=? FOR UPDATE";
            st = conn.prepareStatement(sql);
            st.setInt(1, 1);
            rs = st.executeQuery();
            if (rs.next()) {
                //取出此CLOB对象
                CLOB clob = (CLOB) rs.getClob("CLOBCOL");
                ///向CLOB对象中写入数据
                BufferedWriter out = new BufferedWriter(clob.setCharacterStream(0L));
                //这种方式获取的路径，其中的空格会被使用“%20”代替
                String path = OracleJdbcOperaClob.class.getClassLoader().getResource("data.txt").getPath();
                //将“%20”替换回空格
                path = path.replaceAll("%20", " ");
                BufferedReader in = new BufferedReader(new FileReader(path));
                int c;
                while ((c = in.read()) != -1) {
                    out.write(c);
                }
                in.close();
                out.close();
            }
            //正式提交
            conn.commit();
            System.out.println("修改成功");
        } catch (Exception e) {
            //出错回滚
            conn.rollback();
            e.printStackTrace();

        } finally {
            //恢复原提交状态
            conn.setAutoCommit(defaultCommit);
            JdbcUtils.release(conn, st, rs);
        }
    }

    @Test
    public void clobReplace() throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        conn = JdbcUtils.getConnection();
        boolean defaultCommit = conn.getAutoCommit();
        //开启事务，设定不自动提交
        conn.setAutoCommit(false);
        try {
            /* 清空原CLOB对象 */
            String sql = "UPDATE TEST_CLOB SET CLOBCOL=EMPTY_CLOB() WHERE ID=?";
            st = conn.prepareStatement(sql);
            st.setInt(1, 1);
            st.executeUpdate();

            /* 查询此CLOB对象并锁定 */
            sql = "SELECT CLOBCOL FROM TEST_CLOB WHERE ID=? FOR UPDATE";
            st = conn.prepareStatement(sql);
            st.setInt(1, 1);
            rs = st.executeQuery();

            if (rs.next()) {
                //取出此CLOB对象
                CLOB clob = (CLOB) rs.getClob("CLOBCOL");
                ///向CLOB对象中写入数据
                BufferedWriter out = new BufferedWriter(clob.setCharacterStream(0L));
                //这种方式获取的路径，其中的空格会被使用“%20”代替
                String path = OracleJdbcOperaClob.class.getClassLoader().getResource("data.txt").getPath();
                //将“%20”替换回空格
                path = path.replaceAll("%20", " ");
                BufferedReader in = new BufferedReader(new FileReader(path));
                int c;
                while ((c = in.read()) != -1) {
                    out.write(c);
                }
                in.close();
                out.close();
            }
            //正式提交
            conn.commit();
            System.out.println("替换成功");
        } catch (Exception e) {
            //出错回滚
            conn.rollback();
            e.printStackTrace();

        } finally {
            //恢复原提交状态
            conn.setAutoCommit(defaultCommit);
            JdbcUtils.release(conn, st, rs);
        }
    }
}
