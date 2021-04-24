package com.example.product.entity;

public class Order {
    private String customerName;
    private String address;
    private String email;
    private String phoneNumber;
    private Integer[] productIds;


    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer[] getProductIds() {
        return this.productIds;
    }

    public void setProductIds(Integer[] productIds) {
        this.productIds = productIds;
    }

    public Order() {
    }

    public Order(String customerName, String address, String email, String phoneNumber, Integer[] productIds) {
        this.customerName = customerName;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.productIds = productIds;
    }

}
