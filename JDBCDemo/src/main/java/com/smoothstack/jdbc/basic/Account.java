package com.smoothstack.jdbc.basic;

import java.sql.Date;
import java.util.Objects;

/**
 CREATE TABLE `Account` (
 `accountId` int(11) NOT NULL,
 `accountType` varchar(45) NOT NULL,
 `accountBalance` double NOT NULL,
 `openedDate` date NOT NULL,
 `customerId` int(10) unsigned NOT NULL,
 PRIMARY KEY (`accountId`),
 KEY `custId` (`customerId`),
 CONSTRAINT `custId` FOREIGN KEY (`customerId`) REFERENCES `customer` (`customerid`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
 */
public class Account {
    private int accountId;
    private String accountType; // Checking | Savings | Loan | Credit | CD | Money Market
    private double accountBalance;
    private Date openedDate;
    private int customerId;

    public Account(int accountId, String accountType, double accountBalance, Date openedDate, int customerId) {
        this.accountId = accountId;
        this.accountType = accountType;
        this.accountBalance = accountBalance;
        this.openedDate = openedDate;
        this.customerId = customerId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Date getOpenedDate() {
        return openedDate;
    }

    public void setOpenedDate(Date openedDate) {
        this.openedDate = openedDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accountId == account.accountId && Double.compare(account.accountBalance, accountBalance) == 0 && customerId == account.customerId && accountType.equals(account.accountType) && openedDate.equals(account.openedDate);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", accountType='" + accountType + '\'' +
                ", accountBalance=" + accountBalance +
                ", openedDate=" + openedDate +
                ", customerId=" + customerId +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, accountType, accountBalance, openedDate, customerId);
    }
}
