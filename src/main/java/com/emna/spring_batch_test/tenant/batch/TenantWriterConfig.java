package com.emna.spring_batch_test.tenant.batch;

import javax.sql.DataSource;

import com.emna.spring_batch_test.tenant.dto.DimTenant;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TenantWriterConfig {

    @Bean
    public JdbcBatchItemWriter<DimTenant> tenantWriter(
            @Qualifier("dwDataSource") DataSource dwDataSource) {

        return new JdbcBatchItemWriterBuilder<DimTenant>()
                .dataSource(dwDataSource)
                .sql("""
                        insert into dim_tenant
                        (tenant_id_source, tenant_code, tenant_name, db_name, schema_name, active, effective_from)
                        values
                        (:tenantIdSource, :tenantCode, :tenantName, :dbName, :schemaName, :active, :effectiveFrom)
                        on conflict (tenant_id_source)
                        do update set
                            tenant_code = excluded.tenant_code,
                            tenant_name = excluded.tenant_name,
                            db_name = excluded.db_name,
                            schema_name = excluded.schema_name,
                            active = excluded.active
                        """)
                .beanMapped()
                .build();
    }
}