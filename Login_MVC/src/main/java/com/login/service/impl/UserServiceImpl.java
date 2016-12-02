package com.login.service.impl;


import com.login.dao.IUserDao;
import com.login.dao.impl.UserDaoImpl;
import com.login.domain.User;
import com.login.exception.UserExistException;
import com.login.service.IUserService;

/**
 * Created by yong on 2016/10/31 0031.
 */
public class UserServiceImpl implements IUserService {

    private IUserDao dao = new UserDaoImpl();

    public void registerUser(User user) throws UserExistException {
        if (dao.find(user.getUserName()) != null) {
            throw new UserExistException("注册用户名已经存在！！！");
        }
        dao.add(user);
    }

    public User loginUser(String userName, String userPwd) {
        return dao.find(userName, userPwd);
    }

    public User find(String userName) {
        return dao.find(userName);
    }
}
