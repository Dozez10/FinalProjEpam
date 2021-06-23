package com.example.FinalProjectI.db.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalTime;
import java.util.Objects;
/**
 * Master entity
 * @author Ivan Manuilenko
 */
public class Master {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int masterId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int userId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String userType;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalTime startTime;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalTime endTime;
    private String masterName;
    private double rating;

    public int getMasterId() {
        return masterId;
    }

    public void setMasterId(int masterId) {
        this.masterId = masterId;
    }

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

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Master master = (Master) o;
        return getMasterId() == master.getMasterId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMasterId());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("masterId", masterId)
                .append("userId", userId)
                .append("userType", userType)
                .append("startTime", startTime)
                .append("endTime", endTime)
                .append("masterName", masterName)
                .append("rating", rating)
                .toString();
    }
}

