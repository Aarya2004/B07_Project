package com.example.b07project;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StoreOwnerOrders extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_owner_orders);

        List<Order> items = new ArrayList<Order>();
        items.add(new Order("Joshua", "Banana"));
        items.add(new Order("Joshua1", "Banana3"));
        items.add(new Order("Joshua2", "Banana4"));

        RecyclerView orders = findViewById(R.id.ordersList);
        orders.setLayoutManager(new LinearLayoutManager(this));
        orders.setAdapter(new OrdersViewAdapter(getApplicationContext(), items));
    }
}

