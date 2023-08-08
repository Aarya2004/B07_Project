package com.example.b07project;

import java.util.ArrayList;

class Order {
    String shopperID;
    String shopperName;
    ArrayList<Product> products;
    ArrayList<Integer> productQuantities;
    boolean fulfilled;

    public Order() {}

    public Order(String shopperID, String shopperName, ArrayList<Product> products, ArrayList<Integer> productQuantities, boolean fulfilled) {
        this.shopperID = shopperID;
        this.shopperName = shopperName;
        this.products = products;
        this.productQuantities = productQuantities;
        this.fulfilled = fulfilled;
    }

    public String getShopperID() {
        return shopperID;
    }

    public void setShopperID(String shopperID) {
        this.shopperID = shopperID;
    }

    public String getShopperName() {
        return shopperName;
    }

    public void setShopperName(String shopperName) {
        this.shopperName = shopperName;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Integer> getProductQuantities() {
        return productQuantities;
    }

    public void setProductQuantities(ArrayList<Integer> productQuantities) {
        this.productQuantities = productQuantities;
    }

    public boolean isFulfilled() {
        return fulfilled;
    }

    public void setFulfilled(boolean fulfilled) {
        this.fulfilled = fulfilled;
    }

    public void addOrder(Product product, int quantity) {
        products.add(product);
        productQuantities.add(quantity);
    }
}
