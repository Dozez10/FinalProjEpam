package com.example.FinalProjectI.db.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;
/**
 * MasterService entity
 * @author Ivan Manuilenko
 */
public class MasterService {

    private int masterId;
    private int serviceId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MasterService that = (MasterService) o;
        return getMasterId() == that.getMasterId() && getServiceId() == that.getServiceId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMasterId(), getServiceId());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("masterId", masterId)
                .append("serviceId", serviceId)
                .toString();
    }
}
