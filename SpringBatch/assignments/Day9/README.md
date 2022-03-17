# Assignment

## Control Job Flow

Use the transaction dataset from Day2 to implement a control job flow.

If the date of the transaction is '2018-05-01', you need to make the Step fail, and it should
execute a dummy pagerDutyStep(). Count the number of times the pagerDutyStep() is called and
print that output to the console.

## Column Data Partitioning

Create a ColumnRangePartitioner based on the transaction id in the database table.
The transaction id is associated with a column that you have generated based on auto increment key.
You may have chosen to call it with a different name.

Use the min value and max value of the transactionId and write a partition method that
that creates the appropriate number of ExecutionContext(s) to execute the step.
