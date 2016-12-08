package com.yckjsoft.javaee.el;

/**
 * Created by yong on 2016/10/26 0026.
 */
public class User {
    private String username;

    private String gender;

    private String likes[];


    public User() {
    }

    public User(String username) {
        this.username = username;
    }

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
     * Getter for property 'gender'.
     *
     * @return Value for property 'gender'.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Setter for property 'gender'.
     *
     * @param gender Value to set for property 'gender'.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Getter for property 'likes'.
     *
     * @return Value for property 'likes'.
     */
    public String[] getLikes() {
        return likes;
    }

    /**
     * Setter for property 'likes'.
     *
     * @param likes Value to set for property 'likes'.
     */
    public void setLikes(String[] likes) {
        this.likes = likes;
    }
}
