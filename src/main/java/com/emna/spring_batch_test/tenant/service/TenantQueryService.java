package com.emna.spring_batch_test.tenant.service;

import com.emna.spring_batch_test.tenant.model.TenantInfo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class TenantQueryService {

    private final JdbcTemplate sourceJdbcTemplate;

    public TenantQueryService(@Qualifier("sourceDataSource") DataSource sourceDataSource) {
        this.sourceJdbcTemplate = new JdbcTemplate(sourceDataSource);
    }

    public int countUsersInSchema(String schemaName) {
        String sql = "SELECT COUNT(*) FROM " + schemaName + ".users_table";
        Integer count = sourceJdbcTemplate.queryForObject(sql, Integer.class);
        return count != null ? count : 0;
    }

    public int countUsersForTenant(TenantInfo tenantInfo) {
        return countUsersInSchema(tenantInfo.getTenantSchema());
    }
}