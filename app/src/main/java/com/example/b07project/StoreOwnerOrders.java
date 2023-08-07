package com.example.b07project;

import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StoreOwnerOrders extends AppCompatActivity {
    static String OwnerID = "-619562573";
    static ArrayList<Order> items = new ArrayList<Order>();
    static DatabaseReference ownersRef = FirebaseDatabase.getInstance("https://b07-project-45a16-default-rtdb.firebaseio.com").getReference("Owners");
    //for testing

    public static void updateDatabase(int position, boolean isChecked) {
        ownersRef.child(OwnerID).child("orders").child(Integer.toString(position)).child("fulfilled").setValue(isChecked);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_owner_orders);

        OrdersViewAdapter adapter = new OrdersViewAdapter(getApplicationContext(), items);

        //testing
        ownersRef.child(OwnerID).child("orders").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                items.clear();
                for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                    items.add(childSnapshot.getValue(Order.class));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });

        RecyclerView orders = findViewById(R.id.ordersList);
        orders.setLayoutManager(new LinearLayoutManager(this));
        orders.setAdapter(adapter);
    }
}

