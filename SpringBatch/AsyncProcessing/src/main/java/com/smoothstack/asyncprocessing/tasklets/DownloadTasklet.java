package com.smoothstack.asyncprocessing.tasklets;

import com.sun.jmx.snmp.tasks.Task;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class DownloadTasklet implements Tasklet, StepExecutionListener {
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.println("Download Failed .... ");
        return RepeatStatus.FINISHED;

    }

    @Override
    public void beforeStep(StepExecution stepExecution) {

    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return ExitStatus.FAILED;
    }
}
