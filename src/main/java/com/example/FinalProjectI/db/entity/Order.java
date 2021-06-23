package com.example.FinalProjectI.db.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDateTime;
import java.util.Objects;
/**
 * Order entity
 * @author Ivan Manuilenko
 */
public class Order {
    private int orderId;
    private int userId;
    private int masterId;
    private int serviceId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int timeSlotId;
    private boolean isApplied;
    private boolean isDone;


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMasterId() {
        return masterId;
    }

    public void setMasterId(int masterId) {
        this.masterId = masterId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getTimeSlotId() {
        return timeSlotId;
    }

    public void setTimeSlotId(int timeSlotId) {
        this.timeSlotId = timeSlotId;
    }

    public boolean isApplied() {
        return isApplied;
    }

    public void setApplied(boolean applied) {
        isApplied = applied;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return getOrderId() == order.getOrderId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderId());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("orderId", orderId)
                .append("userId", userId)
                .append("masterId", masterId)
                .append("serviceId", serviceId)
                .append("startTime", startTime)
                .append("endTime", endTime)
                .append("timeSlotId", timeSlotId)
                .append("isApplied", isApplied)
                .append("isDone", isDone)
                .toString();
    }
}
