package com.example.b07project;

import java.util.ArrayList;
import java.util.List;

public class StoreOwner extends Account{
    private String storeName;
    public ArrayList<Order> orders;

    public StoreOwner(String id, String storeName, String email, String password){
        super(id, "StoreOwner", email, password);
        setStoreName(storeName);
        orders = new ArrayList<Order>();

    }

    public String getStoreName(){
        return storeName;
    }

    public void setStoreName(String storeName){
        this.storeName = storeName;
    }
}
