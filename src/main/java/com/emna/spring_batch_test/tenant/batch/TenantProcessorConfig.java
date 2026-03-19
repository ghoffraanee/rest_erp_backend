package com.emna.spring_batch_test.tenant.batch;

import java.time.LocalDateTime;

import com.emna.spring_batch_test.tenant.dto.DimTenant;
import com.emna.spring_batch_test.tenant.dto.TenantRegistry;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TenantProcessorConfig {

    @Bean
    public ItemProcessor<TenantRegistry, DimTenant> tenantProcessor() {
        return source -> {
            DimTenant target = new DimTenant();
            target.setTenantIdSource(source.getTenantId());
            target.setTenantCode(source.getTenantCode());
            target.setTenantName(source.getTenantName());
            target.setDbName(source.getDbName());
            target.setSchemaName(source.getSchemaName());
            target.setActive(source.getActive());
            target.setEffectiveFrom(LocalDateTime.now());
            return target;
        };
    }
}