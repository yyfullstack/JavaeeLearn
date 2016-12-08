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
 * 使用JDBC操作MySQL的二进制数据(例如图像、声音、二进制文)
 */
public class MySQLJdbcOperaBlob {

    @Test
    //向数据库中插入二进制数据
    public void add() {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();
            String sql = "insert into testblob(image) values (?)";
            st = conn.prepareStatement(sql);
            //这种方式获取的路径，其中的空格会被使用“%20”代替
            String path = MySQLJdbcOperaBlob.class.getClassLoader().getResource("car.jpg").getPath();
            //将“%20”替换回空格
            path = path.replaceAll("%20", " ");
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            st.setBinaryStream(1, fis, (int) file.length());
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
    public void read() {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();
            String sql = "select image from testblob WHERE id = ?";
            st = conn.prepareStatement(sql);
            st.setInt(1, 2);
            //执行插入操作，executeUpdate方法返回成功的条数
            rs = st.executeQuery();
            if (rs.next()) {
                //InputStream in = rs.getBlob("image").getBinaryStream();//这种方法也可以
                InputStream in = rs.getBinaryStream("image");
                int len = 0;
                byte[] buffer = new byte[1024];
                FileOutputStream out = new FileOutputStream("d:\\car.jpg");
                while ((len = in.read(buffer)) > 0) {
                    out.write(buffer, 0, len);
                }
                in.close();
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //SQL执行完成之后释放相关资源
            JdbcUtils.release(conn, st, rs);
        }
    }
}
