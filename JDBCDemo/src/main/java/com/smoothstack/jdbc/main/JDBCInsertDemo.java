package com.smoothstack.jdbc.main;

import com.smoothstack.jdbc.basic.Student;
import com.smoothstack.jdbc.basic.StudentUpdateDao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCInsertDemo {
    public static void main(String args[]) throws SQLException, IOException, ClassNotFoundException {
        StudentUpdateDao studentUpdateDao = new StudentUpdateDao();
        List<Student> students = new ArrayList<>();
        students.add(new Student("Bob", "Parker", "bob.parker@reynolds.com"));
        students.add(new Student("Paul", "Harvard", "paul@broward.com"));
        students.add(new Student("Janet", "Jackson", "janet@jackson.com"));
        students.add(new Student("Michael", "Fox", "michael@fox.com"));
        studentUpdateDao.insertStudents("student", students);
        System.out.println("Successfully added students!");
    }
}
