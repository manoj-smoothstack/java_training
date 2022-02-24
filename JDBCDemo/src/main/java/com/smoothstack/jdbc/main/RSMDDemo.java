package com.smoothstack.jdbc.main;

import com.smoothstack.jdbc.basic.StudentDao;

import java.sql.*;

public class RSMDDemo {
    public static void main(String[] args) throws SQLException {
        StudentDao studentDao = new StudentDao();
        System.out.println(studentDao.getColumnNames("student"));
    }

}
