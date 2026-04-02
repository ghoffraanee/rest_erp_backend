package com.emna.spring_batch_test.batch.company.reader;

import com.emna.spring_batch_test.batch.company.dto.CompanyRow;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;

@Configuration
public class CompanyReaderConfig {

    @Bean
    @StepScope
    public JdbcCursorItemReader<CompanyRow> companyReader(
            @Qualifier("sourceDataSource") DataSource sourceDataSource) {

        JdbcCursorItemReader<CompanyRow> reader = new JdbcCursorItemReader<>();
        reader.setDataSource(sourceDataSource);
        reader.setSql("""
                SELECT 
                    id,
                    companyname AS company_name,
                    tenant_schema,
                    city
                FROM public.company
                ORDER BY id
                """);
        reader.setRowMapper(new BeanPropertyRowMapper<>(CompanyRow.class));
        return reader;
    }
}