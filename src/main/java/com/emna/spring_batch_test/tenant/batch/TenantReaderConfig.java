package com.emna.spring_batch_test.tenant.batch;

import javax.sql.DataSource;

import com.emna.spring_batch_test.tenant.dto.TenantRegistry;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TenantReaderConfig {

    @Bean
    public JdbcCursorItemReader<TenantRegistry> tenantReader(
            @Qualifier("superAdminDataSource") DataSource superAdminDataSource) {

        JdbcCursorItemReader<TenantRegistry> reader = new JdbcCursorItemReader<>();
        reader.setDataSource(superAdminDataSource);
        reader.setSql("""
                select tenant_id, tenant_code, tenant_name, db_name, schema_name, active
                from tenant_registry
                where active = true
                order by tenant_id
                """);

        reader.setRowMapper((rs, rowNum) -> {
            TenantRegistry tenant = new TenantRegistry();
            tenant.setTenantId(rs.getLong("tenant_id"));
            tenant.setTenantCode(rs.getString("tenant_code"));
            tenant.setTenantName(rs.getString("tenant_name"));
            tenant.setDbName(rs.getString("db_name"));
            tenant.setSchemaName(rs.getString("schema_name"));
            tenant.setActive(rs.getBoolean("active"));
            return tenant;
        });

        return reader;
    }
}