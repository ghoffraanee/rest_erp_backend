package com.emna.spring_batch_test.customer.service;

import com.emna.spring_batch_test.customer.batch.CustomerReader;
import com.emna.spring_batch_test.customer.dto.DimCustomer;
import com.emna.spring_batch_test.customer.dto.SourceCustomer;
import com.emna.spring_batch_test.employee.service.TenantContext;
import com.emna.spring_batch_test.employee.service.TenantRegistryService;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerLoadService {

    private final com.emna.spring_batch_test.employee.service.TenantRegistryService tenantRegistryService;
    private final CustomerReader customerReader;
    private final ItemProcessor<SourceCustomer, DimCustomer> customerProcessor;
    private final JdbcBatchItemWriter<DimCustomer> customerWriter;

    public CustomerLoadService(
            TenantRegistryService tenantRegistryService,
            CustomerReader customerReader,
            ItemProcessor<SourceCustomer, DimCustomer> customerProcessor,
            JdbcBatchItemWriter<DimCustomer> customerWriter) {
        this.tenantRegistryService = tenantRegistryService;
        this.customerReader = customerReader;
        this.customerProcessor = customerProcessor;
        this.customerWriter = customerWriter;
    }

    public void loadAllCustomers() throws Exception {
        System.out.println("=== START LOAD CUSTOMERS ===");

        List<com.emna.spring_batch_test.employee.service.TenantContext> tenants = tenantRegistryService.getActiveTenants();
        System.out.println("Nombre de tenants actifs trouvés: " + tenants.size());

        for (TenantContext tenant : tenants) {
            System.out.println("Traitement tenant: " + tenant.getTenantName() + " | schema=" + tenant.getSchemaName());

            List<SourceCustomer> sourceCustomers = customerReader.readCustomers(tenant);
            System.out.println("Nombre de clients lus: " + sourceCustomers.size());

            List<DimCustomer> targetCustomers = new ArrayList<>();

            for (SourceCustomer source : sourceCustomers) {
                DimCustomer target = customerProcessor.process(source);
                if (target != null) {
                    targetCustomers.add(target);
                }
            }

            System.out.println("Nombre de clients transformés: " + targetCustomers.size());

            if (!targetCustomers.isEmpty()) {
               customerWriter.write(new Chunk<>(targetCustomers));
                System.out.println("Écriture terminée pour le tenant: " + tenant.getTenantName());
            } else {
                System.out.println("Aucune donnée à écrire pour le tenant: " + tenant.getTenantName());
            }
        }

        System.out.println("=== END LOAD CUSTOMERS ===");
    }
}