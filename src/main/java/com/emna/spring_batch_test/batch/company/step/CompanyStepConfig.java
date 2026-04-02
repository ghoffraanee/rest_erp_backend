package com.emna.spring_batch_test.batch.company.step;

import com.emna.spring_batch_test.batch.company.dto.CompanyRow;
import com.emna.spring_batch_test.dw.dimension.dto.DimCompany;
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
public class CompanyStepConfig {

    @Bean
    public Step loadDimCompanyStep(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            JdbcCursorItemReader<CompanyRow> companyReader,
            ItemProcessor<CompanyRow, DimCompany> companyProcessor,
            JdbcBatchItemWriter<DimCompany> companyWriter) {

        return new StepBuilder("loadDimCompanyStep", jobRepository)
                .<CompanyRow, DimCompany>chunk(10, transactionManager)
                .reader(companyReader)
                .processor(companyProcessor)
                .writer(companyWriter)
                .build();
    }
}