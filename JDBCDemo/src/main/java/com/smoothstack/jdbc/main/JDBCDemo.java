package com.smoothstack.jdbc.main;

import com.smoothstack.jdbc.basic.Student;
import com.smoothstack.jdbc.basic.StudentDao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

class JDBCDemo {
    public static void main(String args[]) throws SQLException, IOException, ClassNotFoundException {
        StudentDao studentDao = new StudentDao();
        List<Student> students = studentDao.getStudents("student");
        if (students == null) {
            System.out.println("We have no students!");
            System.exit(0);
        }
        for (Student student: students) {
            System.out.println(student);
        }
    }
}