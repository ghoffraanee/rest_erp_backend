package com.emna.spring_batch_test.customer.dto;

import java.time.LocalDate;

public class SourceCustomer {

    private Long customerId;
    private String customerCode;
    private String customerName;
    private String city;
    private String country;
    private Boolean active;
    private Long tenantIdSource;
    private String schemaName;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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

    public void setCustomerName(String firstName) {
        this.customerName = firstName;
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

    public Long getTenantIdSource() {
        return tenantIdSource;
    }

    public void setTenantIdSource(Long tenantIdSource) {
        this.tenantIdSource = tenantIdSource;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }
}