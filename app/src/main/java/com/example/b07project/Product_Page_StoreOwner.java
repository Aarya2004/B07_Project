package com.example.b07project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Product_Page_StoreOwner extends AppCompatActivity {
    private name
    private quantity;
    private price;
    private brand;

    private String userID;
    private String name_of_store;

    private Products product;
    private Store store;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        button = (Button) findViewById(R.id);
        button.setOnClickListener(this);

        name = (EditText) findViewById(R.id.name);
        quantity = (EditText) findViewById(R.id.quantity);
        price = (EditText) findViewById(R.id.price);
        brand = (EditText) findViewById(R.id.brand);


        userID = getIntent().getStringExtra("userID");
        name_of_store = getIntent().getStringExtra("name_of_store");
    }

    @Override
    public void onClick(View view)
    {
        int id = view.getId();

        if (id == R.id)
        {
            insert();
            clearAll();
        }
    }

    private void insert()
    {
        Products product = new Products();

        product.product = name.getText().toString();
        product.quantity = quantity.getText().toString();
        product.price = price.getText().toString();
        product.brand = brand.getText().toString();

        @Override
        public void onComplete(@NonNull Task<Void> task)
        {
            Toast.makeText(AddProduct.this, task.isSuccessful() ? "Product Added" : "Could Not Add Product", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearAll()
    {
        name.setText("");
        quantity.setText("");
        price.setText("");
        brand.setText("");

    }
}