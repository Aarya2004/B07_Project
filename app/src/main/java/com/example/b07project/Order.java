package com.example.b07project;

class Order {
    String shopperName;
    String productName;
    int quantity;
    boolean fulfilled;

    public Order(String shopperName, String productName, int quantity, boolean fulfilled) {
        this.shopperName = shopperName;
        this.productName = productName;
        this.quantity = quantity;
        this.fulfilled = fulfilled;
    }

    public String getShopperName() {
        return shopperName;
    }

    public void setShopperName(String shopperName) {
        this.shopperName = shopperName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isFulfilled() {
        return fulfilled;
    }

    public void setFulfilled(boolean fulfilled) {
        this.fulfilled = fulfilled;
    }
}
