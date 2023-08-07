package com.example.b07projectself;

import java.io.Serializable;

public class Product implements Serializable {
    private String brand;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    private String storeName;
    private String name;

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setName(String name) {
        this.name = name;
    }

    private double price;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private String desc;

    public Product(){}
    public Product(String n, String b, double p, String d, String store) {
        brand = b;
        name = n;
        price = p;
        desc = d;
        storeName=store;
    }
}
