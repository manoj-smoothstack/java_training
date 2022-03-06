package com.smoothstack.springbatch.hibernateitemwriter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 CREATE TABLE `product` (
 `product_id` int NOT NULL,
 `prod_name` varchar(45) NOT NULL,
 `price` decimal(15,0) NOT NULL,
 `unit` int NOT NULL,
 `prod_desc` varchar(45) NOT NULL
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 */
@Entity
@Table(name="product")
public class Product {
    @Id
    @Column(name="product_id")
    private Integer productId;

    @Column(name="prod_name")
    private String prodName;

    @Column(name="price")
    private BigDecimal price;

    @Column(name="unit")
    private Integer unit;

    @Column(name="prod_desc")
    private String productDesc;

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productId +
                ", productName='" + prodName + '\'' +
                ", productDesc='" + productDesc + '\'' +
                ", price=" + price +
                ", unit=" + unit +
                '}';
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }
}
