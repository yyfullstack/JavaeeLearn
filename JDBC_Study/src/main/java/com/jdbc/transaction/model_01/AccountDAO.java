package com.jdbc.transaction.model_01;

import com.jdbc.utils.JdbcUtils_C3P0;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by yong on 2016/11/19 0019.
 * 在数据访问层(Dao)中处理事务
 */
public class AccountDAO {
    /**
     * 这个方法是用来处理两个用户之间的转账业务
     * 在开发中，DAO层的职责应该只涉及到CRUD，
     * 而这个transfer方法是处理两个用户之间的转账业务的，已经涉及到具体的业务操作，应该在业务层中做，不应该出现在DAO层的
     * 所以在开发中DAO层出现这样的业务处理方法是完全错误的
     *
     * @param sourceName
     * @param targetName
     * @param money
     * @throws SQLException
     */
    public void transfer(String sourceName, String targetName, float money) throws SQLException {
        Connection conn = null;
        conn = JdbcUtils_C3P0.getConnection();
        try {
            //开启事务
            conn.setAutoCommit(false);
            /**
             * 在创建QueryRunner对象时，不传递数据源给它，是为了保证这两条SQL在同一个事务中进行，
             * 我们手动获取数据库连接，然后让这两条SQL使用同一个数据库连接执行
             */
            QueryRunner runner = new QueryRunner();
            String sqlA = "update account set money=money-100 where name=?";
            String sqlB = "update account set money=money+100 where name=?";
            Object[] paramArr1 = {sourceName};
            Object[] paramArr2 = {targetName};

            runner.update(conn, sqlA, paramArr1);
            //模拟程序出现异常让事务回滚
            int x = 1 / 0;

            runner.update(conn, sqlB, paramArr2);
            //sql正常执行之后就提交事务
            conn.commit();

        } catch (Exception e) {

            e.printStackTrace();
            if (conn != null) {
                //出现异常之后就回滚事务
                conn.rollback();
            }
        } finally {
            conn.close();
        }


    }


}
