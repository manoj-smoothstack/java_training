package com.smoothstack.jdbc.main;

import com.smoothstack.jdbc.basic.StudentUpdateDao;

import java.sql.SQLException;
import java.util.Random;

public class CreateTableDemo {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        StudentUpdateDao studentUpdateDao = new StudentUpdateDao();
        int rand = Math.abs(new Random().nextInt());
        String tableName = "student" + rand;
        System.out.println("Creating table: " + tableName);
        boolean created = studentUpdateDao.createTable("student" + rand);
        if (created) {
            System.out.println("Table " + tableName + " created!");
        } else {
            System.out.println("Table " + tableName + " already exists.");
        }
    }
}
