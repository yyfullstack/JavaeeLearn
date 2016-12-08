package com.jdbc.demo;


import com.jdbc.utils.JdbcUtils;
import oracle.sql.BLOB;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yong on 2016/11/9 0009.
 * Oracle中大数据处理
 */
public class OracleJdbcOperaBlob {

    @Test
    public void blobInsert() throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        conn = JdbcUtils.getConnection();
        boolean defaultCommit = conn.getAutoCommit();
        //开启事务，设定不自动提交
        conn.setAutoCommit(false);
        try {
            //插入一个空的BLOB对象
            String sql = "insert into TEST_BLOB values (?， EMPTY_BLOB())";
            st = conn.prepareStatement(sql);
            st.setInt(1, 2);
            st.executeUpdate();

            // 查询此BLOB对象并锁定
            // 注意: 必 须加for update锁定该行，直至该行被修改完毕，保证不产生并发冲突
            sql = "SELECT BLOBCOL FROM TEST_BLOB WHERE ID=? FOR UPDATE";
            st = conn.prepareStatement(sql);
            st.setInt(1, 2);
            rs = st.executeQuery();

            if (rs.next()) {
                //取出此BLOB对象
                BLOB blob = (BLOB) rs.getBlob("BLOBCOL");
                ///向BLOB对象中写入数据
                BufferedOutputStream out = new BufferedOutputStream(blob.setBinaryStream(0L));
                BufferedInputStream in =
                        new BufferedInputStream(OracleJdbcOperaBlob.class.getClassLoader().getResourceAsStream("car.jpg"));
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
    public void blobRead() throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        conn = JdbcUtils.getConnection();
        boolean defaultCommit = conn.getAutoCommit();
        conn.setAutoCommit(false);
        try {
            String sql = "select * from TEST_BLOB WHERE ID = ?";
            st = conn.prepareStatement(sql);
            st.setInt(1, 1);
            //执行插入操作，executeUpdate方法返回成功的条数
            rs = st.executeQuery();

            if (rs.next()) {
                //获取BLOB对象
                BLOB blob = (BLOB) rs.getBlob("BLOBCOL");
                //以字符形式输出
                BufferedInputStream in = new BufferedInputStream(blob.getBinaryStream());
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("D:\\car.jpg"));
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

    //@Test
    public void blobModify() throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        conn = JdbcUtils.getConnection();
        boolean defaultCommit = conn.getAutoCommit();
        //开启事务，设定不自动提交
        conn.setAutoCommit(false);
        try {
             /* 查询BLOB对象并锁定 */
            String sql = "SELECT BLOBCOL FROM TEST_BLOB WHERE ID=? FOR UPDATE";
            st = conn.prepareStatement(sql);
            st.setInt(1, 1);
            rs = st.executeQuery();

            if (rs.next()) {
                //取出此BLOB对象
                BLOB blob = (BLOB) rs.getBlob("BLOBCOL");
                //向BLOB对象中写入数据
                BufferedOutputStream out = new BufferedOutputStream(blob.setBinaryStream(0L));
                BufferedInputStream in =
                        new BufferedInputStream(OracleJdbcOperaBlob.class.getClassLoader().getResourceAsStream("002.jpg"));

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
    public void blobReplace() throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        conn = JdbcUtils.getConnection();
        boolean defaultCommit = conn.getAutoCommit();
        //开启事务，设定不自动提交
        conn.setAutoCommit(false);
        try {
            /* 清空原BLOB对象 */
            String sql = "UPDATE TEST_BLOB SET BLOBCOL=EMPTY_BLOB() WHERE ID=?";
            st = conn.prepareStatement(sql);
            st.setInt(1, 1);
            st.executeUpdate();

            /* 查询此BLOB对象并锁定 */
            sql = "SELECT BLOBCOL FROM TEST_BLOB WHERE ID=? FOR UPDATE";
            st = conn.prepareStatement(sql);
            st.setInt(1, 1);
            rs = st.executeQuery();

            if (rs.next()) {
                //取出此BLOB对象
                BLOB blob = (BLOB) rs.getBlob("BLOBCOL");
                //向BLOB对象中写入数据
                BufferedOutputStream out = new BufferedOutputStream(blob.setBinaryStream(0L));
                BufferedInputStream in =
                        new BufferedInputStream(OracleJdbcOperaBlob.class.getClassLoader().getResourceAsStream("car.jpg"));

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
