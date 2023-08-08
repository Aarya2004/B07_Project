package com.example.b07project;

public class ShopperMain {

    static Shopper shopper;

    public ShopperMain(Shopper shopper){
        if(this.shopper == null){
            this.shopper = shopper;
        }
    }

    public static Shopper shopperLoggedIn(){
        return shopper;
    }

    public static void shopperLoggedout(){
        shopper = null;
    }
}
