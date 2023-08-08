package com.example.b07project;

public class Product {
    String id;
    String productName;
    int price;

    public Product() {}

    public Product(String id, String name, int price){
        this.id = id;
        this.productName = name;
        this.price = price;
    }

    public String getId(){ return id; }
    public String getProductName(){ return productName; }
    public int getPrice(){ return price; }
    public void setId(String id){ this.id = id; }
    public void setName(String name){ this.productName = name; }
    public void setPrice(int price){ this.price = price; }

}
