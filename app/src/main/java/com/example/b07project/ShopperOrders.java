package com.example.b07project;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;



public class ShopperOrders extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopper_orders);

        List<Order> shopperOrders = new ArrayList<Order>();


        RecyclerView ordersView = findViewById(R.id.shopperOrdersList);
        ordersView.setLayoutManager(new LinearLayoutManager(this));
        ordersView.setAdapter(new ShopperOrdersViewAdapter(getApplicationContext(), shopperOrders));
    }


    public void showOrderStatus(Order order) {
        String status;

        if (order.isFulfilled()) {
            status = "Ready to pick up!";
        } else {
            status = "Order is still being processed.";
        }

        Toast.makeText(this, status, Toast.LENGTH_LONG).show();
    }



}
