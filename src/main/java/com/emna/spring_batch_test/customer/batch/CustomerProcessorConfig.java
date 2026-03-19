package com.emna.spring_batch_test.customer.batch;

import com.emna.spring_batch_test.customer.dto.DimCustomer;
import com.emna.spring_batch_test.customer.dto.SourceCustomer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.time.LocalDateTime;

@Configuration
public class CustomerProcessorConfig {

    @Bean
    public ItemProcessor<SourceCustomer, DimCustomer> customerProcessor(
            @Qualifier("dwDataSource") DataSource dwDataSource) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dwDataSource);

        return source -> {
            Long tenantKey = jdbcTemplate.queryForObject("""
                    select tenant_key
                    from dim_tenant
                    where tenant_id_source = ?
                    """, Long.class, source.getTenantIdSource());

            DimCustomer target = new DimCustomer();
            target.setTenantKey(tenantKey);
            target.setCustomerIdSource(source.getCustomerId());
            target.setCustomerCode(source.getCustomerCode());
            target.setCustomerName(source.getCustomerName());
            target.setCity(source.getCity());
            target.setCountry(source.getCountry());
            target.setActive(source.getActive());
            target.setEffectiveFrom(LocalDateTime.now());

            return target;
        };
    }
}