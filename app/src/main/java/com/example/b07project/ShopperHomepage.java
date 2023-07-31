package com.example.b07project;

import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ShopperHomepage extends AppCompatActivity {
    public void logout(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
