package com.example.b07project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopper_register);
    }
    public void ownerLogin(View view) {
        Intent intent = new Intent(this, StoreOwnerLogin.class);
        startActivity(intent);
    }




}