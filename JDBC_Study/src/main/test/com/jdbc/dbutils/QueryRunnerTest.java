package com.jdbc.dbutils;

import com.jdbc.dbuitls.domain.User;
import com.jdbc.jdbc_framework.JdbcUtils;
import com.jdbc.utils.JdbcUtils_C3P0;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import javax.sql.rowset.serial.SerialClob;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by yong on 2016/11/21 0021.
 * 使用dbutils框架的QueryRunner类完成CRUD,以及批处理
 */
public class QueryRunnerTest {

    private QueryRunner qr = new QueryRunner(JdbcUtils_C3P0.getDs());

    @Test
    public void add() throws SQLException {
        String sql = "insert into users(name, password,email, birthday) values(?,?,?,?)";
        Object[] params = {"tom", "123", "tom@163.com", "1984-09-12"};
        qr.update(sql, params);
    }

    @Test
    public void delete() throws SQLException {
        String sql = "delete from users where id=?";
        qr.update(sql, 32);
    }

    @Test
    public void update() throws SQLException {
        String sql = "update users set name=? where id=?";
        Object[] params = {"ddd", 24};
        qr.update(sql, params);
    }

    @Test
    public void find() throws SQLException {
        String sql = "select * from users where id=?";
        Object[] params = {24};
        User user = qr.query(sql, new BeanHandler<User>(User.class), params);
        System.out.println(user.toString());
    }

    @Test
    public void getAll() throws SQLException {
        String sql = "select * from users";
        List list = qr.query(sql, new BeanListHandler<User>(User.class));
        for (int i = 0; i < list.size(); i++) {
            User user = (User) list.get(i);
            System.out.println(user.toString());
        }
    }

    @Test
    public void testBatch() throws SQLException {
        String sql = "insert into users(name, password, email, birthday) values(?,?,?,?)";
        Object[][] params = new Object[10][];
        for (int i = 0; i < 10; i++) {
            params[i] = new Object[]{"AA" + i, "123456", "aa@sina.com", new Date()};
        }
        qr.batch(sql, params);
    }
    //用dbutils完成大数据（不建议用）
    @Test
    public void testclob() throws SQLException, IOException {
        //QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "insert into testclob(resume) values(?)";  //clob
        //这种方式获取的路径，其中的空格会被使用“%20”代替
        String path = QueryRunnerTest.class.getClassLoader().getResource("data.txt").getPath();
        //将“%20”替换回空格
        path = path.replaceAll("%20", " ");
        FileReader in = new FileReader(path);
        char[] buffer = new char[(int) new File(path).length()];
        in.read(buffer);
        SerialClob clob = new SerialClob(buffer);
        Object params[] = {clob};
        qr.update(sql, params);
    }
}
