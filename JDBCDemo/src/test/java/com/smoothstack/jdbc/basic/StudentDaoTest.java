package com.smoothstack.jdbc.basic;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class StudentDaoTest {

    @Test
    public void testGetStudents() throws SQLException, ClassNotFoundException {
        StudentDao studentDao = new StudentDao();
        StudentUpdateDao studentUpdateDao = new StudentUpdateDao();
        int rand = Math.abs(new Random().nextInt());
        String tableName = "student" + rand;
        System.out.println("Creating table: " + tableName);
        boolean created = studentUpdateDao.createTable(tableName);
        List<Student> students = new ArrayList<>();
        students.add(new Student("Bob", "Parker", "bob.parker@reynolds.com"));
        students.add(new Student("Paul", "Harvard", "paul@broward.com"));
        students.add(new Student("Janet", "Jackson", "janet@jackson.com"));
        students.add(new Student("Michael", "Fox", "michael@fox.com"));
        studentUpdateDao.insertStudents(tableName, students);

        List<Student> studentsReturned = studentDao.getStudents(tableName);
        assertEquals(students, studentsReturned);
        studentUpdateDao.dropTable(tableName);
        System.out.println("Deleted Table: " + tableName);
    }

    @Test
    public void testPrepStudents() throws SQLException, ClassNotFoundException {
        StudentUpdateDao studentUpdateDao = new StudentUpdateDao();
        int rand = Math.abs(new Random().nextInt());
        String tableName = "student" + rand;
        System.out.println("Creating table: " + tableName);
        boolean created = studentUpdateDao.createTable(tableName);
        List<Student> students = new ArrayList<>();
        students.add(new Student("Bob", "Parker", "bob.parker@reynolds.com"));
        students.add(new Student("Paul", "Harvard", "paul@broward.com"));
        students.add(new Student("Janet", "Jackson", "janet@jackson.com"));
        students.add(new Student("Michael", "Fox", "michael@fox.com"));
        studentUpdateDao.insertPrepStudents(tableName, students);

        StudentDao studentDao = new StudentDao();
        List<Student> studentsReturned = studentDao.getStudents(tableName);
        assertEquals(students, studentsReturned);
        studentUpdateDao.dropTable(tableName);
        System.out.println("Deleted Table: " + tableName);
    }

    @Test
    public void testTransaction() throws SQLException, ClassNotFoundException {
        StudentUpdateDao studentUpdateDao = new StudentUpdateDao();
        int rand = Math.abs(new Random().nextInt());
        String tableName = "student" + rand;
        System.out.println("Creating table: " + tableName);
        boolean created = studentUpdateDao.createTable(tableName);
        List<Student> students = new ArrayList<>();
        students.add(new Student("Bob", "Parker", "bob.parker@reynolds.com"));
        students.add(new Student("Paul", "Harvard", "paul@broward.com"));
        students.add(new Student("Janet", "Jackson", "janet@jackson.com"));
        students.add(new Student("Michael", "Fox", "michael@fox.com"));
        studentUpdateDao.insertTransaction(tableName, students);

        StudentDao studentDao = new StudentDao();
        List<Student> studentsReturned = studentDao.getStudents(tableName);
        assertEquals(students, studentsReturned);
        studentUpdateDao.dropTable(tableName);
        System.out.println("Deleted Table: " + tableName);
    }
}