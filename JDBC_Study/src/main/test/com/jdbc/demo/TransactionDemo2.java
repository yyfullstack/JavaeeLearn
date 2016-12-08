package com.jdbc.demo;

import com.jdbc.utils.JdbcUtils;
import org.junit.Test;

import java.sql.*;

/**
 * Created by yong on 2016/11/18 0018.
 * JDBC中使用事务来模似转帐
 * create table account(
 * id int primary key auto_increment,
 * name varchar(40),
 * money float
 * );
 * insert into account(name,money) values('A',1000);
 * insert into account(name,money) values('B',1000);
 * insert into account(name,money) values('C',1000);
 *
 * 有时候可能需要手动设置事务的回滚点，在JDBC中使用如下的语句设置事务回滚点
 * <p>
 * 　　Savepoint sp = conn.setSavepoint();
 * 　　Conn.rollback(sp);
 * 　　Conn.commit();//回滚后必须通知数据库提交事务
 */
public class TransactionDemo2 {
    @Test
    public void testTransaction1() {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        Savepoint sp = null;
        try {
            conn = JdbcUtils.getConnection();
            //通知数据库开启事务 (start transaction)
            conn.setAutoCommit(false);
            String sqlA = "update account set money=money-100 where name='A'";
            st = conn.prepareStatement(sqlA);
            st.executeUpdate();
            //设置事务回滚点
            sp = conn.setSavepoint();

            String sqlB = "update account set money=money+100 where name='B'";
            st = conn.prepareStatement(sqlB);
            st.executeUpdate();

            //程序执行到这里出现异常，后面的sql3语句执行将会中断
            int x = 1 / 0;

            String sqlC = "update account set money=money+100 where name='C'";
            st = conn.prepareStatement(sqlC);
            st.executeUpdate();

            //上面的两条SQL执行Update语句成功之后就通知数据库提交事务(commit)
            conn.commit();
            System.out.println("success");

        } catch (Exception e) {
            try {
                /**
                 * 我们在上面向数据库发送了3条update语句，
                 * sqlC语句由于程序出现异常导致无法正常执行，数据库事务而已无法正常提交，
                 * 由于设置的事务回滚点是在sqlA语句正常执行完成之后，sqlB语句正常执行之前,
                 * 那么通知数据库回滚事务时，不会回滚sqlA执行的update操作
                 * 只会回滚到sqlB执行的update操作，也就是说，上面的三条update语句中，sqlA这条语句的修改操作起作用了
                 * sqlB的修改操作由于事务回滚没有起作用，sqlC由于程序异常没有机会执行
                 */
                conn.rollback(sp);//回滚到设置的事务回滚点
                //回滚了要记得通知数据库提交事务
                conn.commit();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn, st, rs);

        }
    }


}
