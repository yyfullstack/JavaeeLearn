package com.login.service;


import com.login.domain.User;
import com.login.exception.UserExistException;

/**
 * Created by yong on 2016/10/31 0031.
 */
public interface IUserService {

    void registerUser(User user) throws UserExistException;

    User loginUser(String userName, String userPwd);

    User find(String userName);
}
