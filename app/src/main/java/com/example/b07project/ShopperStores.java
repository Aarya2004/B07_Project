package com.example.b07project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

public class ShopperStores extends AppCompatActivity {

    FirebaseDatabase firedb;
    public void logout(View view){
        Intent intent = new Intent(this, MainActivity.class);
        ShopperMain.shopperLoggedout();
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopper_stores);
        firedb = FirebaseDatabase.getInstance("https://b07-project-45a16-default-rtdb.firebaseio.com/");
        ArrayList<Store> stores = new ArrayList<Store>();
        RecyclerView storeRecyclerView = findViewById(R.id.storeRecyclerView);
        storeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ShopperStoreViewAdapter storeAdapter = new ShopperStoreViewAdapter(getApplicationContext(), stores);
        storeRecyclerView.setAdapter(storeAdapter);
        get_stores(stores, storeAdapter);
    }

    public void get_stores(ArrayList<Store> stores, ShopperStoreViewAdapter storeAdapter){
        DatabaseReference db = this.firedb.getReference();
        DatabaseReference query = db.child("Owners");
        query.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for(DataSnapshot id: snapshot.getChildren()){
                    String ownerId = String.valueOf(id.getKey());
                    String ownerEmail = String.valueOf(id.child("email").getValue());
                    String ownerPass = String.valueOf(id.child("password").getValue());
                    String storeName = String.valueOf(id.child("Store Name").getValue());
                    StoreOwner owner = new StoreOwner(ownerId, storeName, ownerEmail, ownerPass);
                    Store store = new Store(owner);
                    DataSnapshot products = id.child("products");
                    if(products.hasChildren()){
                        for(DataSnapshot productId: products.getChildren()){
                            String pId = String.valueOf(productId.getKey());
                            String productName = String.valueOf(productId.child("name").getValue());
                            int productPrice = Integer.parseInt(String.valueOf(productId.child("price").getValue()));
                            Product product = new Product(pId, productName, productPrice);
                            store.addProduct(product);
                        }
                    }
                    stores.add(store);
                }
                storeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }

//    public void createStoreLayout(Store store) {
//        // Create a new layout for the store
//        LinearLayout storeLayout = new LinearLayout(this);
//        storeLayout.setOrientation(LinearLayout.VERTICAL);
//
//        // Create and add a TextView for store name
//        TextView storeNameTextView = new TextView(this);
//        storeNameTextView.setText(store.getOwner().getStoreName());
//        storeLayout.addView(storeNameTextView);
//
//        // Loop through products and add TextViews for each product
//        for (Product product : store.getProducts()) {
//            TextView productTextView = new TextView(this);
//            productTextView.setText(product.getName() + " - $" + product.getPrice());
//            storeLayout.addView(productTextView);
//        }
//
//        // Add the store layout to the main layout
//        LinearLayout mainLayout = findViewById(R.id.mainLayout);
//        mainLayout.addView(storeLayout);
//    }
}
