package com.example.b07project;

public class StoreOwner extends Account{
    private String storeName;

    public StoreOwner(String storeName, String email, String password){
        super("StoreOwner", email, password);
        setStoreName(storeName);
    }

    public String getStoreName(){
        return storeName;
    }

    public void setStoreName(String storeName){
        this.storeName = storeName;
    }
}
