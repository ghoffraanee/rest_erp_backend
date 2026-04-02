package com.emna.spring_batch_test.dw.dimension.dto;

public class DimCompany {

    private Integer companyId;
    private String companyName;
    private String tenantSchema;
    private String City;

    public DimCompany() {
    }

    public DimCompany(Integer companyId, String companyName, String tenantSchema, String City) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.tenantSchema = tenantSchema;
        this.City = City;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTenantSchema() {
        return tenantSchema;
    }

    public void setTenantSchema(String tenantSchema) {
        this.tenantSchema = tenantSchema;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }
}