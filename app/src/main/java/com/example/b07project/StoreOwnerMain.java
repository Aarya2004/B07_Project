package com.example.b07project;

public class StoreOwnerMain {
    StoreOwner owner;

    public StoreOwnerMain(StoreOwner owner){
        this.owner = owner;
    }

    public StoreOwner ownerLoggedIn(){
        return owner;
    }
}
