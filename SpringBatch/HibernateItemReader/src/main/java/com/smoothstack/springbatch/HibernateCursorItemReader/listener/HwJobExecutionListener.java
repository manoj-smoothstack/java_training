package com.smoothstack.springbatch.HibernateCursorItemReader.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class HwJobExecutionListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("before starting the Job - Job Name:" + jobExecution.getJobInstance().getJobName());
        System.out.println("before starting the Job" + jobExecution.getExecutionContext().toString());

        jobExecution.getExecutionContext().put("my name", "michael");
        System.out.println("before starting the Job - after set" + jobExecution.getExecutionContext().toString());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("after starting the Job - Job Execution Context" + jobExecution.getExecutionContext().toString());

    }
}
