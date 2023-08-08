package com.example.b07projectself;

import java.io.Serializable;

public class Order implements Serializable {
    private String product;
    private int quantity;
    private boolean completed;
    private String storeName;
    private String shopper;
    private String productName;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private double price;

    public String getProductName() {
        return productName;
    }

    public Order() {
        product = "None";
        quantity = 1;
        completed = true;
        storeName = "";
        shopper = "";
    }
    public Order(String item, String name, int quantity, double price, String store, String user, boolean completed) {
        product = item;
        productName = name;
        this.quantity=quantity;
        storeName=store;
        shopper=user;
        this.completed=completed;
        this.price = price;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getShopper() {
        return shopper;
    }

    public void setShopper(String shopper) {
        this.shopper = shopper;
    }
    public void setProductName(String s) {
        productName = s;
    }
}
