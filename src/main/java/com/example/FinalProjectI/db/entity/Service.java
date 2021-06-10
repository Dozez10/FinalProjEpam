package com.example.FinalProjectI.db.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;

public class Service {
    private int serviceId ;
    private String serviceType;
    private double servicePrice;

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public double getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(double servicePrice) {
        this.servicePrice = servicePrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return getServiceId() == service.getServiceId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getServiceId());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("serviceId", serviceId)
                .append("serviceType", serviceType)
                .append("servicePrice", servicePrice)
                .toString();
    }
}
