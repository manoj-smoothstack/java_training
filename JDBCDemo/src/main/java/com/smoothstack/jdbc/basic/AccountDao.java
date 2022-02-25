package com.smoothstack.jdbc.basic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDao {
    public List<Account> getAccounts(String tableName) throws SQLException {
        List<Account> accounts = null;
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/hb_student_tracker?" +
                    "user=root&password=StrongPassword1$");

            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery("select accountId, accountType, accountBalance, openedDate, customerId from " + tableName + ";");
            while (rset.next()) {
                if (accounts == null) accounts = new ArrayList<>();
                Integer accountId = rset.getInt(1);
                String accountType = rset.getString(2);
                Double accountBalance = rset.getDouble(3);
                Date openedDate = rset.getDate(4);
                Integer customerId = rset.getInt(5);
                Account account = new Account(accountId, accountType, accountBalance, openedDate, customerId);
                accounts.add(account);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) conn.close();
        }
        return accounts;
    }
}
