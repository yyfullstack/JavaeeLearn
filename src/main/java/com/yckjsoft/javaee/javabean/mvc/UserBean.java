package com.yckjsoft.javaee.javabean.mvc;

import java.io.Serializable;

/**
 * Created by yong on 2016/10/28 0028.
 * 模型(model),即业务对象，即javabean对象，包含设置，获取数据的方法。包含业务方法
 */
public class UserBean implements Serializable {
    private String username;

    private String password;

    /**
     * Getter for property 'username'.
     *
     * @return Value for property 'username'.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for property 'username'.
     *
     * @param username Value to set for property 'username'.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter for property 'password'.
     *
     * @return Value for property 'password'.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for property 'password'.
     *
     * @param password Value to set for property 'password'.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    //校验用户的输入数据
    public boolean validate() {
        if ("".equals(username) || "".equals(password)) {
            return false;
        }
        return true;
    }

    //业务逻辑处理方法
    public boolean login() {
        if ("admin".equals(username) && "123456".equals(password)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
