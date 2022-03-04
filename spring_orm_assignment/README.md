# SpringORM Assignment

## 1. Question Answer Database

Setup a MySQL schema using MYSQL Workbench with a number of tables that allow you
to store questions, multiple choices, correct answer choices and free form official
answers. You may need more than a handful of tables to do this.

Publish the schema using Mysql workbench as a PDF file and also the individual SQL
files uploaded to your github page.

## 2. Create One Spring Bean

Implement a spring bean for just the Question table.

## 3. Use Spring Data JPA

Use Spring Data JPA to save Question table to the database. Tghe Spring Data JPA
should automatically create the Question table if it does not already exist and 
add the questions to it. It should have the primary key and the auto increment set
for the question id column.

## 4. Write a Spring DataJpaTest

Write a Spring DataJpaTest that saves the Question bean to an H2 database. It should
use the create-destroy DDL.
