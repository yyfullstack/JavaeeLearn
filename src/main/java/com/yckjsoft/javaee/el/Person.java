package com.yckjsoft.javaee.el;

/**
 * Created by yong on 2016/11/5 0005.
 */
public class Person {
    private String name;
    private int age;
    private Address address;

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
     * Getter for property 'address'.
     *
     * @return Value for property 'address'.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Setter for property 'address'.
     *
     * @param address Value to set for property 'address'.
     */
    public void setAddress(Address address) {
        this.address = address;
    }
}
