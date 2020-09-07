package com.example.service.model;

public class Product {

    private Long productID;
    private String productName;
    private Integer productPrice;

    public Product() {
        super();
    }

    public Product(Long productID, String productName, Integer productPrice) {
        super();
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }
}
