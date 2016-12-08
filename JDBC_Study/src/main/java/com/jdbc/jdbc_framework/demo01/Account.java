package com.jdbc.jdbc_framework.demo01;

import java.io.Serializable;

/**
 * Created by yong on 2016/10/30 0030.
 */
public class Account implements Serializable {

    private static final long serialVersionUID = 5930765030012059619L;

    private int id;

    private String name;

    private Float money;

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
     * Getter for property 'money'.
     *
     * @return Value for property 'money'.
     */
    public Float getMoney() {
        return money;
    }

    /**
     * Setter for property 'money'.
     *
     * @param money Value to set for property 'money'.
     */
    public void setMoney(Float money) {
        this.money = money;
    }
}
