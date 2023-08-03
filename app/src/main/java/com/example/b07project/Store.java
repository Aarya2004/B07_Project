package com.example.b07project;

import java.util.ArrayList;

public class Store {
    String storeName;
    StoreOwner owner;
    ArrayList<Product> products;
    ArrayList<Order> orders;

    public Store(StoreOwner owner){
        this.storeName = owner.getStoreName();
        this.owner = owner;
        products = new ArrayList<Product>();
        orders = new ArrayList<Order>();
    }

    public String getStoreName(){ return storeName; }

    public StoreOwner getOwner(){ return owner; }

    public ArrayList<Product> getProducts(){ return products; }

    public ArrayList<Order> getOrders(){ return orders; }

    public void setStoreName(String storeName){ this.storeName = storeName;}

    public void setStoreOwner(StoreOwner owner){ this.owner = owner; }

    public void addProduct(Product product){ products.add(product); }

    public void removeProduct(Product product){ products.remove(product); }

    public void addOrder(Order order){ orders.add(order); }

    public void removeOrder(Order order){ orders.remove(order); }
}
