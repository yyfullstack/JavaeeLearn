package com.jdbc.demo;


import com.jdbc.utils.JdbcUtils;
import org.junit.Test;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yong on 2016/11/9 0009.
 * 使用JDBC操作MySQL的大文本
 */
public class MySQLJdbcOperaClob {
    //向数据库中插入大文本数据
    @Test
    public void add() {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        Reader reader = null;

        try {
            conn = JdbcUtils.getConnection();
            String sql = "insert into testclob(resume) values (?)";
            st = conn.prepareStatement(sql);
            //这种方式获取的路径，其中的空格会被使用“%20”代替
            String path = MySQLJdbcOperaClob.class.getClassLoader().getResource("data.txt").getPath();
            //将“%20”替换回空格
            path = path.replaceAll("%20", " ");

            File file = new File(path);
            reader = new FileReader(file);
            System.out.println("len--------->" + file.length());
            st.setCharacterStream(1, reader, (int) file.length());
            int num = st.executeUpdate();
            if (num > 0) {
                System.out.println("插入成功！");
            }
            //关闭流
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
    }

    @Test
    // 读取数据库中的大文本数据
    public void read() {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "select resume from testclob WHERE id = ?";
            st = conn.prepareStatement(sql);
            st.setInt(1, 3);
            //执行插入操作，executeUpdate方法返回成功的条数
            rs = st.executeQuery();
            String contentStr = "";
            String content = "";

            if (rs.next()) {
                //使用resultSet.getString("字段名")获取大文本数据的内容
                content = rs.getString("resume");
                //使用resultSet.getCharacterStream("字段名")获取大文本数据的内容
                Reader reader = rs.getCharacterStream("resume");
                char[] buffer = new char[1024];
                int len = 0;
                FileWriter out = new FileWriter("D:\\data.txt");
                while ((len = reader.read(buffer)) > 0) {
                    contentStr += new String(buffer);
                    out.write(buffer, 0, len);
                }
                out.close();
                reader.close();
            }
            System.out.println(content);
            System.out.println("-------------------------------");
            System.out.println(contentStr);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //SQL执行完成之后释放相关资源
            JdbcUtils.release(conn, st, rs);
        }
    }
}
