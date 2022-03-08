# Assignment

## 1. Find the problem with the following code and fix it.

```
import org.springframework.boot.autoconfigure.SpringApplication;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
@EnableBatchProcessing
@SpringApplication
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyApplication, args);
    }
}
```

## 2. Find the problem with the following code and fix it.

```
import org.springframework.context.annotation.Job;
@Job
public Job helloWorldJob() {  // Job is an interface
    return jobs.get("helloWorldJob").start(step1()).build();
}
```

## 3. Identify the correct statements.

```
A. A JobInstance is a single attempt to run a job.
B. A JobBuilder needs to be implemented by a user in order to create jobs.
C. The JobExecutionListener object is optional.
D. A task consists of tasklets.
```


## 4. What is this definition referring to?

It is an enum, which can take values of continuable or finished. 
The continuable indicates that processing can continue.

## 5. Find the problem with the following code and fix it.

```
public Tasklet creditTasklet() {
    return (new Tasklet() (
        @Override
        public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
            // processing credit transaction!
            return RepeatStatus.FINISHED;
        }
    ));
}
```
