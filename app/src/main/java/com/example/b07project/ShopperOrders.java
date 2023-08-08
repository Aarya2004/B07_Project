package com.example.b07project;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShopperOrders extends AppCompatActivity {
    static String ShopperID;
    static List<Order> shopperOrders = new ArrayList<>();
    static DatabaseReference shoppersRef = FirebaseDatabase.getInstance("https://b07-project-45a16-default-rtdb.firebaseio.com").getReference("Shoppers");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopper_orders);
        ShopperID = ShopperMain.shopperLoggedIn().getId();

        ShopperOrdersViewAdapter adapter = new ShopperOrdersViewAdapter(getApplicationContext(), shopperOrders);

        shoppersRef.child(ShopperID).child("orders").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                shopperOrders.clear();
                for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                    shopperOrders.add(childSnapshot.getValue(Order.class));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });

        RecyclerView orders = findViewById(R.id.shopperOrdersList);
        orders.setLayoutManager(new LinearLayoutManager(this));
        orders.setAdapter(adapter);
    }
}
