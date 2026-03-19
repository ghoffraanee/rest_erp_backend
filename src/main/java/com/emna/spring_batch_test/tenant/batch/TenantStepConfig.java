package com.emna.spring_batch_test.tenant.batch;

import com.emna.spring_batch_test.tenant.dto.DimTenant;
import com.emna.spring_batch_test.tenant.dto.TenantRegistry;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class TenantStepConfig {

    @Bean
    public Step loadDimTenantStep(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            JdbcCursorItemReader<TenantRegistry> tenantReader,
            ItemProcessor<TenantRegistry, DimTenant> tenantProcessor,
            JdbcBatchItemWriter<DimTenant> tenantWriter) {

        return new StepBuilder("loadDimTenantStep", jobRepository)
                .<TenantRegistry, DimTenant>chunk(10, transactionManager)
                .reader(tenantReader)
                .processor(tenantProcessor)
                .writer(tenantWriter)
                .build();
    }
}