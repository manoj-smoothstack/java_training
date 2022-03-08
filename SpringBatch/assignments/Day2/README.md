# Assignment

## CSV Reader

Example the transactions.csv.gz file in this directory and write an ItemReader that supports CSV.
Tune the chunk size to 100 and demonstrate that you can write a sample back using a ConsoleWriter.
Perform a count of the number of records read and print that information to the terminal.

You will need to create a BatchConfig.java file using @Configuration that shows the job, step, reader, listener
and writer. 

Here is an example schema:

```
date, county, area (m2), number, total area (m2), average area (m2), total transaction amount (eur), minimum transaction amount (eur), maximum transaction amount (eur), unit price (eur / m2) ) minimum, unit price (eur / m2) maximum, unit price (eur / m2) median, unit price (eur / m2) average, unit price (eur / m2) standard deviation, month, year, index
2005-01-01, Harju County, "10-29.99", 65,1418.8,21.83,1135773,959,124628,81.24,4499.2,716.58,766.69,614.42,1,2005,97.54
2005-01-01, Harju County, "30-40.99", 155,5431.5,35.04,4009365,639,62633,16.51,1605.99,746.18,738.23,272.33,1,2005,97.54
2005-01-01, Harju County, "41-54.99", 253,12043.8,47.6,8674474,1314,127823,29.07,2834.22,712.91,719.91,337.66,1,2005,97.54
2005-01-01, Harju County, "55-69.99", 230,14515.9,63.11,10115291,639,137410,10.63,2114,662.88,699.59,306.17,1,2005,97.54
2005-01-01, Harju County, "70-249.99", 118,10905,92.42,9952072,639,370688,3.79,2913.02,851.51,886.06,445.59,1,2005,97.54
2005-01-01, Hiiu county, "41-54.99", 1,51.2,51.2, NA, NA, NA, NA, NA, NA, NA, NA, 1,2005,97.54
2005-01-01, Hiiu county, "55-69.99", 1,67.6,67.6, NA, NA, NA, NA, NA, NA, NA, NA, 1,2005,97.54
```

## Create a schema

Create a transaction table in MYSQL with the schema that represents this CSV file shown above.
Commit the CREATE TABLE statement as transaction.sql file.

## Load transaction table

Load transaction.sql into the transaction table using the LOAD DATA command in sql. Verify the number of records 
in transaction table with the number of rows in the original CSV file.

Commit your loader.sql file and the bash script that counted the number of rows in transaction.csv file.

## Hibernate Reader

Write a Hibernate ItemReader that reads data from the transaction table. You will need to develop the Transaction.java
class and implement the BatchConfig.java file along with the job, step, reader, listener and writer.

You should be able to demonstrate how many records you read from the transaction table.

## Step and Job Executions

Demonstrate how many StepExecutions happened (use the beforeStep and afterStep functions).
Demonstrate how many JobExecutions happened (use the beforeJob and afterJob functions).

Verify how the number of StepExecutions correlate with the number of records in transaction table or CSV file.
Verify how the number of JobExecutions correlate with the number of records in transaction table or CSV file.
