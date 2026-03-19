package com.emna.spring_batch_test.tenant.dto;

import java.time.LocalDateTime;

public class DimTenant {

    private Long tenantIdSource;
    private String tenantCode;
    private String tenantName;
    private String dbName;
    private String schemaName;
    private Boolean active;
    private LocalDateTime effectiveFrom;

    public Long getTenantIdSource() {
        return tenantIdSource;
    }

    public void setTenantIdSource(Long tenantIdSource) {
        this.tenantIdSource = tenantIdSource;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
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