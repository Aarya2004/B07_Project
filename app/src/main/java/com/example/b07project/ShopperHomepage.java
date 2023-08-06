package com.example.b07project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ShopperHomepage extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopper_home);
    }

    public void storeRedirect(View view) {
        Intent intent = new Intent(this, ShopperStores.class);
        startActivity(intent);
    }

    public void shopperOrderRedirect(View view) {
        Intent intent = new Intent(this, ShopperOrders.class);
        startActivity(intent);
    }



    public void logout(View view){
        Intent intent = new Intent(this, MainActivity.class);
        ShopperMain.shopperLoggedout();
        startActivity(intent);
    }
}
