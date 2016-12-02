package com.yckjsoft.javaee.javabean;

import java.util.Date;

/**
 * Created by yong on 2016/10/26 0026.
 */
public class Person {
    private String name;

    private String sex;

    private int age;

    private boolean married;

    private Date birthday;

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Person() {
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
     * Getter for property 'sex'.
     *
     * @return Value for property 'sex'.
     */
    public String getSex() {
        return sex;
    }

    /**
     * Setter for property 'sex'.
     *
     * @param sex Value to set for property 'sex'.
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * Getter for property 'age'.
     *
     * @return Value for property 'age'.
     */
    public int getAge() {
        return age;
    }

    /**
     * Setter for property 'age'.
     *
     * @param age Value to set for property 'age'.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Getter for property 'married'.
     *
     * @return Value for property 'married'.
     */
    public boolean isMarried() {
        return married;
    }

    /**
     * Setter for property 'married'.
     *
     * @param married Value to set for property 'married'.
     */
    public void setMarried(boolean married) {
        this.married = married;
    }
}
