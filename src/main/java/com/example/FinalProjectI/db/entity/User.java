package com.example.FinalProjectI.db.entity;


import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;
/**
 * User entity
 * @author Ivan Manuilenko
 */
public class User {
    private int userId;
    private String userType;
    private String userPassword;
    private String userEmail;
    private String userLogin;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getUserId() == user.getUserId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userId", userId)
                .append("userType", userType)
                .append("userPassword", userPassword)
                .append("userEmail", userEmail)
                .append("userLogin", userLogin)
                .toString();
    }
}
