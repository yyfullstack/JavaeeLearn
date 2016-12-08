package com.jdbc.transaction.model_04;

import com.jdbc.jdbc_framework.demo01.Account;

import java.sql.SQLException;

/**
 * Created by yong on 2016/11/19 0019.
 */
public class AccountService {

    //在业务层处理两个账户之间的转账问题
    public void transfer(int sourceId, int targetId, float money) throws SQLException {

        AccountDAO dao = new AccountDAO();

        Account source = dao.find(sourceId);
        Account target = dao.find(targetId);

        source.setMoney(source.getMoney() - money);
        target.setMoney(target.getMoney() + money);

        dao.update(source);

        int x = 1 / 0;

        dao.update(target);
    }


}
