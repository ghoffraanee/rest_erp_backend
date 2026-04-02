package com.emna.spring_batch_test.tenant.model;

public class TenantInfo {

    private Integer id;
    private String companyName;
    private String tenantSchema;
    private String City;

    public TenantInfo(Integer id, String companyName, String tenantSchema, String City) {
        this.id = id;
        this.companyName = companyName;
        this.tenantSchema = tenantSchema;
        this.City = City;
    }

    public Integer getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getTenantSchema() {
        return tenantSchema;
    }

    public String getCity() {
        return City;
    }

    @Override
    public String toString() {
        return "TenantInfo{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", tenantSchema='" + tenantSchema + '\'' +
                ", City=" + City +
                '}';
    }
}