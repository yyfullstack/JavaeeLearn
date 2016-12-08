package com.jdbc.transaction.model_03;

import com.jdbc.jdbc_framework.demo01.Account;

import java.sql.SQLException;

/**
 * Created by yong on 2016/11/19 0019.
 */
public class AccountService {

    //在业务层处理两个账户之间的转账问题
    public void transfer(int sourceId, int targetId, float money) throws SQLException {

        try {
            //开启事务，在业务层处理事务，保证dao层的多个操作在同一个事务中进行
            JdbcUtilsWithThreadLocal.startTransaction();

            AccountDAO dao = new AccountDAO();

            Account source = dao.find(sourceId);
            Account target = dao.find(targetId);

            source.setMoney(source.getMoney() - money);
            target.setMoney(target.getMoney() + money);

            dao.update(source);

            int x = 1 / 0;

            dao.update(target);
            //SQL正常执行之后提交事务
            JdbcUtilsWithThreadLocal.commit();
        } catch (Exception e) {
            e.printStackTrace();
            //出现异常之后就回滚事务
            JdbcUtilsWithThreadLocal.rollback();
        } finally {
            //关闭数据库连接
            JdbcUtilsWithThreadLocal.close();
        }
    }


}
