package com.example.FinalProjectI.db.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * TimeSlot entity
 * @author Ivan Manuilenko
 */
public class TimeSlot {
    private int timeSlotId;
    private int  masterId ;
    private LocalDateTime startTime ;
    private LocalDateTime endTime ;
    boolean isFree;

    public int getTimeSlotId() {
        return timeSlotId;
    }

    public void setTimeSlotId(int timeSlotId) {
        this.timeSlotId = timeSlotId;
    }

    public int getMasterId() {
        return masterId;
    }

    public void setMasterId(int masterId) {
        this.masterId = masterId;
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

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeSlot timeSlot = (TimeSlot) o;
        return getTimeSlotId() == timeSlot.getTimeSlotId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTimeSlotId());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("timeSlotId", timeSlotId)
                .append("masterId", masterId)
                .append("startTime", startTime)
                .append("endTime", endTime)
                .append("isFree", isFree)
                .toString();
    }
}
