package com.jdbc.transaction.model_03;

import com.jdbc.jdbc_framework.demo01.Account;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * Created by yong on 2016/11/19 0019.
 * 针对Account对象的CRUD
 */
public class AccountDAO {

    public void update(Account account) throws SQLException {
        QueryRunner qr = new QueryRunner();
        String sql = "update account set name=?, money=? where id=?";
        Object[] params = {account.getName(), account.getMoney(), account.getId()};
        //JdbcUtilsWithThreadLocal.getConnection()获取当前线程中的Connection对象
        qr.update(JdbcUtilsWithThreadLocal.getConnection(), sql, params);
    }

    public Account find(int id) throws SQLException {
        QueryRunner qr = new QueryRunner();
        String sql = "select * from account where id=?";
        Object[] params = {id};
        return (Account) qr.query(JdbcUtilsWithThreadLocal.getConnection(), sql, new BeanHandler(Account.class), params);
    }
}
