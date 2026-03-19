package com.emna.spring_batch_test.customer.batch;

import com.emna.spring_batch_test.customer.dto.DimCustomer;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class CustomerWriterConfig {

    @Bean
    public JdbcBatchItemWriter<DimCustomer> customerWriter(
            @Qualifier("dwDataSource") DataSource dwDataSource) {

        return new JdbcBatchItemWriterBuilder<DimCustomer>()
                .dataSource(dwDataSource)
                .sql("""
                        insert into dim_customer
                        (tenant_key, customer_id_source, customer_code, customer_name,
                         city, country, active, effective_from)
                        values
                        (:tenantKey, :customerIdSource, :customerCode, :customerName,
                         :city, :country, :active, :effectiveFrom)
                        on conflict (tenant_key, customer_id_source)
                        do update set
                            customer_code = excluded.customer_code,
                            customer_name = excluded.customer_name,
                            city = excluded.city,
                            country = excluded.country,
                            active = excluded.active
                        """)
                .beanMapped()
                .build();
    }
}