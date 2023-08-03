package com.example.b07project;

public class Product {
    String id;
    String name;
    int price;

    public Product(String id, String name, int price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId(){ return id; }
    public String getProductName(){ return name; }
    public int getPrice(){ return price; }
    public void setId(String id){ this.id = id; }
    public void setName(String name){ this.name = name; }
    public void setPrice(int price){ this.price = price; }

}
