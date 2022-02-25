package com.smoothstack.jdbc.basic;

import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerAccountDaoTest {

    @Test
    public void testCustomerAccount() throws SQLException, ClassNotFoundException {
        CustomerDao customerDao = new CustomerDao();
        CustomerAccountUpdateDao customerAccountUpdateDao = new CustomerAccountUpdateDao();
        int rand = Math.abs(new Random().nextInt());
        //String tableName = "customer" + rand;
        //System.out.println("Creating table: " + tableName);
        //boolean created = customerAccountUpdateDao.createTable(tableName);
        Customer customer = new Customer(0, "Jean Luc Ponty");
        int customerId = customerAccountUpdateDao.insertCustomer("customer", customer);
        Integer accountId = Math.abs(new Random().nextInt());
        String accountType = "Checking";
        double accountBalance = 2000;
        Date openedDate = new Date(3535353935L);
        Account account = new Account(accountId, accountType, accountBalance, openedDate, customerId);
        customerAccountUpdateDao.insertAccount("account", account);

        //assertEquals(students, studentsReturned);
        //studentUpdateDao.dropTable(tableName);
        //System.out.println("Deleted Table: " + tableName);
    }
}