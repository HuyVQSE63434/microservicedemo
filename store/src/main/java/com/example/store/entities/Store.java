package com.example.store.entities;

import java.util.List;

public class Store {
    
    private Integer id;
    private List<Object> products;


    public Store() {
    }

    public Store(Integer id, List<Object> products) {
        this.id = id;
        this.products = products;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Object> getProducts() {
        return this.products;
    }

    public void setProducts(List<Object> products) {
        this.products = products;
    }

}
