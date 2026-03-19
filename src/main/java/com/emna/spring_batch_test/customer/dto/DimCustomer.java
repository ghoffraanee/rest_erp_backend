package com.emna.spring_batch_test.customer.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DimCustomer {

    private Long tenantKey;
    private Long customerIdSource;
    private String customerCode;
    private String customerName;
    private String city;
    private String country;
    private Boolean active;
    private LocalDateTime effectiveFrom;

    public Long getTenantKey() {
        return tenantKey;
    }

    public void setTenantKey(Long tenantKey) {
        this.tenantKey = tenantKey;
    }

    public Long getCustomerIdSource() {
        return customerIdSource;
    }

    public void setCustomerIdSource(Long customerIdSource) {
        this.customerIdSource = customerIdSource;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDateTime getEffectiveFrom() {
        return effectiveFrom;
    }

    public void setEffectiveFrom(LocalDateTime effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
    }
}