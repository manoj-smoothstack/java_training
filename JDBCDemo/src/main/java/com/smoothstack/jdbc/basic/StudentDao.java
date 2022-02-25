package com.smoothstack.jdbc.basic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    public List<Student> getStudents(String tableName) throws SQLException, ClassNotFoundException {
        List<Student> students = null;
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/hb_student_tracker?" +
                    "user=root&password=StrongPassword1$");

            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery("select first_name, last_name, email from " + tableName + ";");
            while (rset.next()) {
                if (students == null) students = new ArrayList<>();
                String first_name = rset.getString(1);
                String last_name = rset.getString(2);
                String email = rset.getString(3);
                Student student = new Student(first_name, last_name, email);
                students.add(student);
            }
        } finally {
            if (conn != null) conn.close();
        }
        return students;
    }
    public List<String> getColumnNames(String table) throws SQLException {
        List<String> colNames = null;
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/hb_student_tracker?" +
                    "user=root&password=StrongPassword1$");

            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery("select * from " + table + " limit 1;");
            ResultSetMetaData rsmd = rset.getMetaData(); // HW do we need data in table
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                if (colNames == null) colNames = new ArrayList<>();
                colNames.add(rsmd.getColumnName(i+1));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) conn.close();
        }
        return colNames;
    }
}