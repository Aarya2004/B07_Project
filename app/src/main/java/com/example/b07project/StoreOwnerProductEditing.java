package com.example.b07project;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StoreOwnerProductEditing extends AppCompatActivity
{
        private List<Product> products;
        private RecyclerView productView;


        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            products = new ArrayList<>();

            products.add(new Product("Product 1", "Calvin Klein", 50, 1));
            products.add(new Product("Product 2", "Gucci", 650, 1));
        }
}

