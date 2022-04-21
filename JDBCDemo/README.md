# Assignment

## Database Assignment

- Install mysql on your computer if not already done.
- Install mysqlworkbench on your computer if not already done.
- Create a user "sstack" with a defined password.
- Create a database by name "smoothstack".
- Create a table called "School" with schema as below.

```
id - a number (primary key, auto increment)
name - a string
```

- Create another table called "Student" with schema as below.

```
id - a number (primary key, auto increment)
name - a string
schoolId - a foreign key to id in School table
```

## JDBC assignment

Implement the following:

- School.java
- Student.java
- SchoolDao.java
- SchoolUpdateDao.java
- StudentDao.java
- StudentUpdateDao.java

Write a JUnit test that will test the following:

- Automate the creation of temporary tables School and Student.
- Create a sample school record for school "Green Oaks High School".
- Obtain the last inserted id.
- Add two students to this school "Bob Anderson", "Janet Collins".
- Automate the deletion of temporary tables School and Student.
