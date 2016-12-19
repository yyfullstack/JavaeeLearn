package com.smart4j.chapter2.domain;

import java.io.Serializable;

/**
 * Created by yong on 2016/10/30 0030.
 * 客户
 */
public class Customer implements Serializable {


    private static final long serialVersionUID = 4259745642926293781L;
    private int id;

    private String name;

    private String contact;

    private String telephone;

    private String email;

    private String remark;

    /**
     * Getter for property 'id'.
     *
     * @return Value for property 'id'.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for property 'id'.
     *
     * @param id Value to set for property 'id'.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for property 'name'.
     *
     * @return Value for property 'name'.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for property 'name'.
     *
     * @param name Value to set for property 'name'.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for property 'contact'.
     *
     * @return Value for property 'contact'.
     */
    public String getContact() {
        return contact;
    }

    /**
     * Setter for property 'contact'.
     *
     * @param contact Value to set for property 'contact'.
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * Getter for property 'telephone'.
     *
     * @return Value for property 'telephone'.
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Setter for property 'telephone'.
     *
     * @param telephone Value to set for property 'telephone'.
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
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
     * Getter for property 'remark'.
     *
     * @return Value for property 'remark'.
     */
    public String getRemark() {
        return remark;
    }

    /**
     * Setter for property 'remark'.
     *
     * @param remark Value to set for property 'remark'.
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
