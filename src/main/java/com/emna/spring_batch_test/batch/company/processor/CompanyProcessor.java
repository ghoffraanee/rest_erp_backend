package com.emna.spring_batch_test.batch.company.processor;

import com.emna.spring_batch_test.batch.company.dto.CompanyRow;
import com.emna.spring_batch_test.dw.dimension.dto.DimCompany;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class CompanyProcessor implements ItemProcessor<CompanyRow, DimCompany> {

    @Override
    public DimCompany process(CompanyRow item) {
        return new DimCompany(
                item.getId(),
                item.getCompanyName(),
                item.getTenantSchema(),
                item.getCity()
        );
    }
}