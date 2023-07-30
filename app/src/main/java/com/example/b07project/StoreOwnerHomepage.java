package com.example.b07project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class StoreOwnerHomepage extends AppCompatActivity {

    StoreOwner User;

    public StoreOwnerHomepage(StoreOwner User){
        this.User = User;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_owner_homepage);
    }

    public void productRedirect(View view) {
        Intent intent = new Intent(this, ShopperLogin.class);
        startActivity(intent);
    }


    public void orderRedirect(View view) {
        Intent intent = new Intent(this, StoreOwnerLogin.class);
        startActivity(intent);
    }

    public void logout(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
