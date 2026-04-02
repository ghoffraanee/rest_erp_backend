package com.emna.spring_batch_test.batch.company.writer;

import com.emna.spring_batch_test.dw.dimension.dto.DimCompany;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class CompanyWriterConfig {

    @Bean
    public JdbcBatchItemWriter<DimCompany> companyWriter(
            @Qualifier("dwDataSource") DataSource dwDataSource) {

        return new JdbcBatchItemWriterBuilder<DimCompany>()
                .dataSource(dwDataSource)
                .sql("""
                        INSERT INTO dim_company (company_id, company_name, tenant_schema, city)
                        VALUES (:companyId, :companyName, :tenantSchema, :city)
                        ON CONFLICT (company_id) DO NOTHING
                        """)
                .beanMapped()
                .build();
    }
}