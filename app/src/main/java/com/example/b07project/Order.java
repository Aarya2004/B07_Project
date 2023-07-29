package com.example.b07project;

class Order {
    String shopperName;
    String productName;

    public Order(String shopperName, String productName) {
        this.shopperName = shopperName;
        this.productName = productName;
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
}
