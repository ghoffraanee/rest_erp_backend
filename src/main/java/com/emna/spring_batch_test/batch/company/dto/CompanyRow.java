package com.emna.spring_batch_test.batch.company.dto;

public class CompanyRow {
    private Integer id;
    private String companyName;
    private String tenantSchema;
    private String City;

    public CompanyRow() {
    }

    public CompanyRow(Integer id, String companyName, String tenantSchema, String City) {
        this.id = id;
        this.companyName = companyName;
        this.tenantSchema = tenantSchema;
        this.City = City;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
