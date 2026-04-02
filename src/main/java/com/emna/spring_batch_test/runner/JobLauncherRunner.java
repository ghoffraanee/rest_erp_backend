package com.emna.spring_batch_test.runner;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class JobLauncherRunner implements CommandLineRunner {

    private final JobLauncher jobLauncher;
    private final Job loadDimCompanyJob;

    public JobLauncherRunner(JobLauncher jobLauncher, Job loadDimCompanyJob) {
        this.jobLauncher = jobLauncher;
        this.loadDimCompanyJob = loadDimCompanyJob;
    }

    @Override
    public void run(String... args) throws Exception {
        JobParameters params = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        jobLauncher.run(loadDimCompanyJob, params);
    }
}