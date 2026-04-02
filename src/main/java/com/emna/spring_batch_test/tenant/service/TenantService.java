package com.emna.spring_batch_test.tenant.service;

import com.emna.spring_batch_test.tenant.model.TenantInfo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service
public class TenantService {

    private final JdbcTemplate sourceJdbcTemplate;

    public TenantService(@Qualifier("sourceDataSource") DataSource sourceDataSource) {
        this.sourceJdbcTemplate = new JdbcTemplate(sourceDataSource);
    }

    public List<TenantInfo> getAllTenants() {
        String sql = """
                SELECT id, companyname, tenant_schema, city
                FROM public.company
                ORDER BY id
                """;

        return sourceJdbcTemplate.query(sql, (rs, rowNum) ->
                new TenantInfo(
                        rs.getInt("id"),
                        rs.getString("companyname"),
                        rs.getString("tenant_schema"),
                        rs.getString("city")
                )
        );
    }
}