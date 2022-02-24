package com.smoothstack.jdbc.main;

import com.smoothstack.jdbc.basic.StudentUpdateDao;

import java.io.IOException;
import java.sql.SQLException;

public class JDBCDeleteDemo {
    public static void main(String args[]) throws SQLException, IOException, ClassNotFoundException {
        StudentUpdateDao studentUpdateDao = new StudentUpdateDao();
        studentUpdateDao.deleteStudent("student", "Janet", "Jackson", "janet@jackson.com");
    }
}
