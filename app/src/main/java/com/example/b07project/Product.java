package com.example.b07project;

public class Product {
    public String name;

    public String brand;
    public double price;
    public int quantity;

    public Product(String name, String brand, double price, int quantity) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters and setters
    public String getName() {
        return name;
    }
    public String getBrand() {
        return brand;
    }
    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

