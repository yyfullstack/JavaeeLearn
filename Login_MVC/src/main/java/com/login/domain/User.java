package com.login.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by yong on 2016/10/30 0030.
 */
public class User implements Serializable {

    private static final long serialVersionUID = 5930765030012059619L;

    private String id;

    private String userName;

    private String userPwd;

    private String email;

    private Date birthday;

    /**
     * Getter for property 'id'.
     *
     * @return Value for property 'id'.
     */
    public String getId() {
        return id;
    }

    /**
     * Setter for property 'id'.
     *
     * @param id Value to set for property 'id'.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter for property 'userName'.
     *
     * @return Value for property 'userName'.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Setter for property 'userName'.
     *
     * @param userName Value to set for property 'userName'.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Getter for property 'userPwd'.
     *
     * @return Value for property 'userPwd'.
     */
    public String getUserPwd() {
        return userPwd;
    }

    /**
     * Setter for property 'userPwd'.
     *
     * @param userPwd Value to set for property 'userPwd'.
     */
    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    /**
     * Getter for property 'email'.
     *
     * @return Value for property 'email'.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter for property 'email'.
     *
     * @param email Value to set for property 'email'.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter for property 'birthday'.
     *
     * @return Value for property 'birthday'.
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * Setter for property 'birthday'.
     *
     * @param birthday Value to set for property 'birthday'.
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }


}
