package com.emna.spring_batch_test.runner;

import com.emna.spring_batch_test.customer.service.CustomerLoadService;
import com.emna.spring_batch_test.employee.service.EmployeeLoadService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class JobStarter implements CommandLineRunner {

    private final JobLauncher jobLauncher;
    private final Job loadDimTenantJob;
    private final EmployeeLoadService employeeLoadService;
    private final CustomerLoadService customerLoadService;

    public JobStarter(
            JobLauncher jobLauncher,
            @Qualifier("loadDimTenantJob") Job loadDimTenantJob,
            EmployeeLoadService employeeLoadService, CustomerLoadService customerLoadService) {
        this.jobLauncher = jobLauncher;
        this.loadDimTenantJob = loadDimTenantJob;
        this.employeeLoadService = employeeLoadService;
        this.customerLoadService = customerLoadService;
    }

    @Override
    public void run(String... args) throws Exception {
        jobLauncher.run(
                loadDimTenantJob,
                new JobParametersBuilder()
                        .addLong("time", System.currentTimeMillis())
                        .toJobParameters()
        );
        employeeLoadService.loadAllEmployees();
        customerLoadService.loadAllCustomers();
    }
}