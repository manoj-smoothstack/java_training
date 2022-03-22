package com.smoothstack.springbatch.processtransactions.model;

import java.sql.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 CREATE TABLE `transaction` (
 `transactionId` int NOT NULL,
 `date` datetime NOT NULL,
 `county` varchar(45) NOT NULL,
 `area` varchar(45) NOT NULL,
 `number` int NOT NULL,
 `totalArea` float NOT NULL,
 `avgArea` float NOT NULL,
 `totalTransactionAmt` int NOT NULL,
 `minTransactionAmt` int NOT NULL,
 `maxTransactionAmt` int NOT NULL,
 `unitPriceMax` float NOT NULL,
 `unitPriceMed` float NOT NULL,
 `unitPriceAvg` float NOT NULL,
 `unitPriceStddev` float NOT NULL,
 `month` int NOT NULL,
 `year` int NOT NULL,
 `index` float NOT NULL,
 PRIMARY KEY (`transactionId`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 */

@Entity
@Table(name="transaction")
public class Transaction {

    @Id
    @Column(name="transactionId")
    private Integer transactionId;

    @Column(name="date")
    private Date date;

    @Column(name="county")
    private String county;

    @Column(name="area")
    private String area;

    @Column(name="number")
    private Integer number;

    @Column(name="totalArea")
    private Float totalArea;

    @Column(name="avgArea")
    private Float avgArea;

    @Column(name="totalTransactionAmt")
    private Integer totalTransactionAmt;

    @Column(name="minTransactionAmt")
    private Integer minTransactionAmt;

    @Column(name="maxTransactionAmt")
    private Integer maxTransactionAmt;

    private Float unitPriceMax;
    private Float unitPriceMed;
    private Float unitPriceAvg;
    private Float unitPriceStddev;
    private Integer month;
    private Integer year;
    private Float index;

    public Transaction(Date date, String county, String area, Integer number, Float totalArea,
                       Float avgArea, Integer totalTransactionAmt, Integer minTransactionAmt,
                       Integer maxTransactionAmt, Float unitPriceMax, Float unitPriceMed,
                       Float unitPriceAvg, Float unitPriceStddev, Integer month, Integer year, Float index) {
        this.date = date;
        this.county = county;
        this.area = area;
        this.number = number;
        this.totalArea = totalArea;
        this.avgArea = avgArea;
        this.totalTransactionAmt = totalTransactionAmt;
        this.minTransactionAmt = minTransactionAmt;
        this.maxTransactionAmt = maxTransactionAmt;
        this.unitPriceMax = unitPriceMax;
        this.unitPriceMed = unitPriceMed;
        this.unitPriceAvg = unitPriceAvg;
        this.unitPriceStddev = unitPriceStddev;
        this.month = month;
        this.year = year;
        this.index = index;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "date=" + date +
                ", county='" + county + '\'' +
                ", area='" + area + '\'' +
                ", number=" + number +
                ", totalArea=" + totalArea +
                ", avgArea=" + avgArea +
                ", totalTransactionAmt=" + totalTransactionAmt +
                ", minTransactionAmt=" + minTransactionAmt +
                ", maxTransactionAmt=" + maxTransactionAmt +
                ", unitPriceMax=" + unitPriceMax +
                ", unitPriceMed=" + unitPriceMed +
                ", unitPriceAvg=" + unitPriceAvg +
                ", unitPriceStddev=" + unitPriceStddev +
                ", month=" + month +
                ", year=" + year +
                ", index=" + index +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(date, that.date) && Objects.equals(county, that.county) && Objects.equals(area, that.area) && Objects.equals(number, that.number) && Objects.equals(totalArea, that.totalArea) && Objects.equals(avgArea, that.avgArea) && Objects.equals(totalTransactionAmt, that.totalTransactionAmt) && Objects.equals(minTransactionAmt, that.minTransactionAmt) && Objects.equals(maxTransactionAmt, that.maxTransactionAmt) && Objects.equals(unitPriceMax, that.unitPriceMax) && Objects.equals(unitPriceMed, that.unitPriceMed) && Objects.equals(unitPriceAvg, that.unitPriceAvg) && Objects.equals(unitPriceStddev, that.unitPriceStddev) && Objects.equals(month, that.month) && Objects.equals(year, that.year) && Objects.equals(index, that.index);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, county, area, number, totalArea, avgArea, totalTransactionAmt, minTransactionAmt, maxTransactionAmt, unitPriceMax, unitPriceMed, unitPriceAvg, unitPriceStddev, month, year, index);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Float getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(Float totalArea) {
        this.totalArea = totalArea;
    }

    public Float getAvgArea() {
        return avgArea;
    }

    public void setAvgArea(Float avgArea) {
        this.avgArea = avgArea;
    }

    public Integer getTotalTransactionAmt() {
        return totalTransactionAmt;
    }

    public void setTotalTransactionAmt(Integer totalTransactionAmt) {
        this.totalTransactionAmt = totalTransactionAmt;
    }

    public Integer getMinTransactionAmt() {
        return minTransactionAmt;
    }

    public void setMinTransactionAmt(Integer minTransactionAmt) {
        this.minTransactionAmt = minTransactionAmt;
    }

    public Integer getMaxTransactionAmt() {
        return maxTransactionAmt;
    }

    public void setMaxTransactionAmt(Integer maxTransactionAmt) {
        this.maxTransactionAmt = maxTransactionAmt;
    }

    public Float getUnitPriceMax() {
        return unitPriceMax;
    }

    public void setUnitPriceMax(Float unitPriceMax) {
        this.unitPriceMax = unitPriceMax;
    }

    public Float getUnitPriceMed() {
        return unitPriceMed;
    }

    public void setUnitPriceMed(Float unitPriceMed) {
        this.unitPriceMed = unitPriceMed;
    }

    public Float getUnitPriceAvg() {
        return unitPriceAvg;
    }

    public void setUnitPriceAvg(Float unitPriceAvg) {
        this.unitPriceAvg = unitPriceAvg;
    }

    public Float getUnitPriceStddev() {
        return unitPriceStddev;
    }

    public void setUnitPriceStddev(Float unitPriceStddev) {
        this.unitPriceStddev = unitPriceStddev;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Float getIndex() {
        return index;
    }

    public void setIndex(Float index) {
        this.index = index;
    }
}
