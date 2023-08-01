package com.example.b07project;

public class StoreOwnerMain {
    static StoreOwner owner;

    public StoreOwnerMain(StoreOwner owner){
        if(this.owner == null){
            this.owner = owner;
        }
    }

    public StoreOwner ownerLoggedIn(){
        return owner;
    }

    public static void ownerLoggedout(){
        owner = null;
    }
}
