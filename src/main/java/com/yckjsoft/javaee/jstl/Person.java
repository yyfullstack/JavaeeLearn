package com.yckjsoft.javaee.jstl;

/**
 * Created by yong on 2016/10/26 0026.
 * 包名称：JSTL
 * 类名称：Person
 * 类描述：一个只有getter和setter方法的JavaBean或者说一个pojo(简单的Java对象(Plain Old Java Objects))类，
 * 作为一个vo（数据传输对象）。定义了四个变量age、name、sex和home。
 */
public class Person {
    private String name;

    private String sex;

    private String age;

    private String home;

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
    public String getAge() {
        return age;
    }

    /**
     * Setter for property 'age'.
     *
     * @param age Value to set for property 'age'.
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     * Getter for property 'home'.
     *
     * @return Value for property 'home'.
     */
    public String getHome() {
        return home;
    }

    /**
     * Setter for property 'home'.
     *
     * @param home Value to set for property 'home'.
     */
    public void setHome(String home) {
        this.home = home;
    }
}
