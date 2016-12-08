package com.jdbc.transaction.model_02;

import com.jdbc.jdbc_framework.demo01.Account;
import com.jdbc.utils.JdbcUtils_C3P0;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by yong on 2016/11/19 0019.
 * 业务逻辑处理层
 */
public class AccountService {
    /**
     * 这个方法是用来处理两个用户之间的转账业务
     * @param sourceId
     * @param targetId
     * @param money
     * @throws SQLException
     */
    public void transfer(int sourceId, int targetId, float money) throws SQLException {
        Connection conn = null;
        try {
            //获取数据库连接
            conn = JdbcUtils_C3P0.getConnection();
            conn.setAutoCommit(false);

            //将获取到的Connection传递给AccountDao，
            // 保证dao层使用的是同一个Connection对象操作数据库
            AccountDAO dao = new AccountDAO(conn);

            Account source = dao.find(sourceId);
            Account target = dao.find(targetId);

            source.setMoney(source.getMoney() - money);
            target.setMoney(target.getMoney() + money);

            dao.update(source);

            int x = 1 / 0;

            dao.update(target);

            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            conn.rollback();
        } finally {
            conn.close();
        }
    }


}
