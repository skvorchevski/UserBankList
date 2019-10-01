package com.gmail.askvorchevski.service.model;

public class UserDTO {
    private Integer userId;
    private String name;
    private String sureName;

    public UserDTO(Integer userId, String name, String sureName) {
        this.userId = userId;
        this.name = name;
        this.sureName = sureName;
    }

    public UserDTO() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSureName() {
        return sureName;
    }

    public void setSureName(String sureName) {
        this.sureName = sureName;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", sureName='" + sureName + '\'' +
                '}';
    }
}
