package com.smoothstack.jdbc.basic;

import java.sql.*;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class CustomerAccountUpdateDao {
    public int insertCustomer(String tableName, Customer customer) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/hb_student_tracker?" +
                    "user=root&password=StrongPassword1$");

            String query = "insert into " + tableName + " set customerName = '" + customer.getCustomerName() + "'";
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            int lastInsertedId  = stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            conn.commit();
            conn.setAutoCommit(true);
            return lastInsertedId;
        } finally {
            if (conn != null) conn.close();
        }
    }
    public void insertAccount(String tableName, Account account) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/hb_student_tracker?" +
                    "user=root&password=StrongPassword1$");

            String query = "insert into " + tableName + " set accountId = '" + account.getAccountId() +
                    "', accountType = '" + account.getAccountType() +
                    "', accountBalance = " + account.getAccountBalance() +
                    ", openedDate = '" + account.getOpenedDate() +
                    "', customerId = " + account.getCustomerId();
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
            conn.commit();
            conn.setAutoCommit(true);
        } finally {
            if (conn != null) conn.close();
        }
    }
}
