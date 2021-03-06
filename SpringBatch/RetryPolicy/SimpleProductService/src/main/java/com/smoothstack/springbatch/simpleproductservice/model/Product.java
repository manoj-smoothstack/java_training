package com.smoothstack.springbatch.simpleproductservice.model;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

public class Product {

    private Integer productId;

    private String prodName;
  
    private BigDecimal price;
    private Integer unit;
    private String productDesc;

    public Product(Integer productId, String prodName, BigDecimal price, Integer unit, String productDesc) {
        this.productId = productId;
        this.prodName = prodName;
        this.price = price;
        this.unit = unit;
        this.productDesc = productDesc;
    }

    public Product() {
    }

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
