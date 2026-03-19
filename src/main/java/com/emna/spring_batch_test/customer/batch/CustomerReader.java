package com.emna.spring_batch_test.customer.batch;

import com.emna.spring_batch_test.customer.dto.SourceCustomer;
import com.emna.spring_batch_test.employee.service.TenantContext;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class CustomerReader {

    private final JdbcTemplate jdbcTemplate;

    public CustomerReader(@Qualifier("erpDataSource") DataSource erpDataSource) {
        this.jdbcTemplate = new JdbcTemplate(erpDataSource);
    }

    public List<SourceCustomer> readCustomers(TenantContext tenant) {
        String schema = tenant.getSchemaName();

        String sql = """
                select
                    customer_id,
                    customer_code,
                    customer_name,
                    city,
                    country,
                    active
                from %s.customer
                order by Customer_id
                """.formatted(schema);

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            SourceCustomer customer = new SourceCustomer();
            customer.setCustomerId(rs.getLong("customer_id"));
            customer.setCustomerCode(rs.getString("customer_code"));
            customer.setCustomerName(rs.getString("customer_name"));
            customer.setCity(rs.getString("city"));
            customer.setCountry(rs.getString("country"));
            customer.setActive(rs.getBoolean("active"));
            customer.setTenantIdSource(tenant.getTenantIdSource());
            customer.setSchemaName(schema);
            return customer;
        });
    }
}