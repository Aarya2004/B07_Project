package com.example.b07projectself;

import java.util.ArrayList;
import java.util.List;

public class Store {
    public List<String> getProducts() {
        return products;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String owner;

    public void setProducts(List<String> products) {
        this.products = products;
    }

    private List<String> products;

    public void addProduct(String pid) {
        if (products == null) products = new ArrayList<>();
        products.add(pid);
    }

    public Store() {
        products = new ArrayList<>();
    };
}