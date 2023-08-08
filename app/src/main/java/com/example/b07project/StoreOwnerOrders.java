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
    static String OwnerID;
    static ArrayList<Order> items = new ArrayList<Order>();
    static DatabaseReference ownersRef = FirebaseDatabase.getInstance("https://b07-project-45a16-default-rtdb.firebaseio.com").getReference("Owners");

    public static void updateDatabase(int position, boolean isChecked) {
        ownersRef.child(OwnerID).child("orders").child(Integer.toString(position)).child("fulfilled").setValue(isChecked);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_owner_orders);
        OwnerID = StoreOwnerMain.ownerLoggedIn().getId();
//        OwnerID = "-619562573"; //testing
        OrdersViewAdapter adapter = new OrdersViewAdapter(getApplicationContext(), items);
//        ArrayList<Product> testProducts = new ArrayList<Product>();
//        ArrayList<Integer> testQuantities = new ArrayList<Integer>();
//        testProducts.add(new Product("id1", "Television", 0));
//        testProducts.add(new Product("id2", "Banana", 0));
//        testProducts.add(new Product("id3", "Cheese", 2));
//        for (Product product: testProducts) {
//            Log.e("demo", product.productName);
//        }
//        testQuantities.add(1);
//        testQuantities.add(15);
//        testQuantities.add(12);
//        items.add(new Order(null, "Joshua", testProducts, testQuantities, false));
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

