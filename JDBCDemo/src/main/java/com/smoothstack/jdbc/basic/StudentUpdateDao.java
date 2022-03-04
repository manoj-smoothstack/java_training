package com.smoothstack.jdbc.basic;

import java.sql.*;
import java.util.List;

public class StudentUpdateDao {
    /**
     * This method only should be executed within a connection wrapper.
     * @param conn
     * @param tableName
     * @return
     * @throws SQLException
     */
    private boolean tableExistsOnConn(Connection conn, String tableName) throws SQLException {
        DatabaseMetaData dbmd = conn.getMetaData();
        ResultSet resultSet = dbmd.getTables(null, null, tableName, new String[] {"TABLE"});
        return resultSet.next();
    }
    public boolean tableExists(String tableName) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/hb_student_tracker?" +
                    "user=root&password=StrongPassword1$");
            return tableExistsOnConn(conn, tableName);
        } finally {
            if (conn != null) conn.close();
        }
    }
    public void dropTable(String tableName) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/hb_student_tracker?" +
                    "user=root&password=StrongPassword1$");
            boolean tableExists = tableExistsOnConn(conn, tableName);
            if (tableExists) {
                Statement stmt = conn.createStatement();
                stmt.executeUpdate("drop table " + tableName);
            }
        } finally {
            if (conn != null) conn.close();
        }
    }
    public boolean createTable(String tableName) throws SQLException, ClassNotFoundException {
        /**
          CREATE TABLE `student` (
            `id` int(11) NOT NULL AUTO_INCREMENT,
            `first_name` varchar(45) DEFAULT NULL,
            `last_name` varchar(45) DEFAULT NULL,
            `email` varchar(45) DEFAULT NULL,
            PRIMARY KEY (`id`)
          ) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1
         */
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hb_student_tracker?" +
                    "user=root&password=StrongPassword1$");
            boolean tableExists = tableExistsOnConn(conn, tableName);
            if (tableExists) return false;

            Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE TABLE `" + tableName + "` (\n" +
                    "            `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "            `first_name` varchar(45) DEFAULT NULL,\n" +
                    "            `last_name` varchar(45) DEFAULT NULL,\n" +
                    "            `email` varchar(45) DEFAULT NULL,\n" +
                    "            PRIMARY KEY (`id`)\n" +
                    "          ) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1");
            return true;
        } finally {
            if (conn != null) conn.close();
        }
    }
    public void insertStudents(String tableName, List<Student> students) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/hb_student_tracker?" +
                    "user=root&password=StrongPassword1$");

            Statement stmt = conn.createStatement();
            for (Student student: students) {
                stmt.executeUpdate("insert into " + tableName + " set first_name = '" + student.getFirst_name() +
                        "', last_name = '" + student.getLast_name() +
                        "', email = '" + student.getEmail() + "'");
            }
        } finally {
            if (conn != null) conn.close();
        }
    }
    public void insertPrepStudents(String tableName, List<Student> students) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/hb_student_tracker?" +
                    "user=root&password=StrongPassword1$");

            String query = "insert into " + tableName + " set first_name = ?, last_name = ?, email = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            for (Student student: students) {
                //stmt.setString(1, tableName);
                stmt.setString(1, student.getFirst_name());
                stmt.setString(2, student.getLast_name());
                stmt.setString(3, student.getEmail());
                stmt.executeUpdate();
            }
        } finally {
            if (conn != null) conn.close();
        }
    }
    public void insertTransaction(String tableName, List<Student> students) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/hb_student_tracker?" +
                    "user=root&password=StrongPassword1$");

            String query = "insert into " + tableName + " set first_name = ?, last_name = ?, email = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            conn.setAutoCommit(false);
            for (Student student: students) {
                //stmt.setString(1, tableName);
                stmt.setString(1, student.getFirst_name());
                stmt.setString(2, student.getLast_name());
                stmt.setString(3, student.getEmail());
                stmt.executeUpdate();
            }
            conn.commit();
            conn.setAutoCommit(true);
        } finally {
            if (conn != null) conn.close();
        }
    }
    public void updateStudent(String tableName, String first_name, String last_name, String email) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/hb_student_tracker?" +
                    "user=root&password=StrongPassword1$");

                Statement stmt = conn.createStatement();
                stmt.executeUpdate("update " + tableName + " set first_name = '" + first_name +
                        "', last_name = '" + last_name +
                        "' where email = '" + email + "'");
        } finally {
            if (conn != null) conn.close();
        }
    }

    public void deleteStudent(String tableName, String first_name, String last_name, String email) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/hb_student_tracker?" +
                    "user=root&password=StrongPassword1$");
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("delete from " + tableName + " where email = '" + email + "'");
        } finally {
            if (conn != null) conn.close();
        }
    }
    public void deleteAllStudents(String tableName) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/hb_student_tracker?" +
                    "user=root&password=StrongPassword1$");
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("delete from " + tableName);
        } finally {
            if (conn != null) conn.close();
        }
    }
}
