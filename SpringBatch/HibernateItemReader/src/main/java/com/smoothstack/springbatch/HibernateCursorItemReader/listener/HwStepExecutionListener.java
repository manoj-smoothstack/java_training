package com.smoothstack.springbatch.HibernateCursorItemReader.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class HwStepExecutionListener  implements StepExecutionListener {
    @Override
    public void beforeStep(StepExecution stepExecution) {

        System.out.println("this is from Before Step Execution" + stepExecution.getJobExecution().getExecutionContext());

        System.out.println(" In side Step - print job paramters" + stepExecution.getJobExecution().getJobParameters());
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("this is from After Step Execution" + stepExecution.getJobExecution().getExecutionContext());
        return null;
    }
}
