package com.jeffosmond.phase04.entity;

import lombok.ToString;

import java.util.Date;

@ToString
public class User {
    private Long userId;

    private String userName;

    private Byte userSex;

    private Date birthday;

    private String address;

    public User(Long userId, String userName, Byte userSex, Date birthday, String address) {
        this.userId = userId;
        this.userName = userName;
        this.userSex = userSex;
        this.birthday = birthday;
        this.address = address;
    }

    public User() {
        super();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Byte getUserSex() {
        return userSex;
    }

    public void setUserSex(Byte userSex) {
        this.userSex = userSex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }
}