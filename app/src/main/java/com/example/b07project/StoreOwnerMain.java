package com.example.b07project;

public class StoreOwnerMain {
    static StoreOwner owner;
    static Store store;

    public StoreOwnerMain(StoreOwner owner){
        if(this.owner == null){
            this.owner = owner;
        }
        if(store == null){
            store = new Store(owner);;
        }
    }

    public StoreOwner ownerLoggedIn(){
        return owner;
    }

    public Store storeLoggedIn(){ return store; }

    public static void ownerLoggedout(){
        owner = null;
        store = null;
    }
}
