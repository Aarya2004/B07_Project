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
        //testing
        items.add(new Order("Joshua", "Banana", 25, false));
        items.add(new Order("Joshua1", "Banana3", 21, true));
        items.add(new Order("Joshua2", "Banana4", 0, false));

        RecyclerView orders = findViewById(R.id.ordersList);
        orders.setLayoutManager(new LinearLayoutManager(this));
        orders.setAdapter(new OrdersViewAdapter(getApplicationContext(), items));
    }
}

