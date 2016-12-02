package com.login.dao;


import com.login.domain.User;

/**
 * Created by yong on 2016/10/31 0031.
 */
public interface IUserDao {

    User find(String userName, String userPwd);

    void add(User user);

    User find(String userName);
}
