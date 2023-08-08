package com.example.b07projectself;


import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class OwnerActivity extends AppCompatActivity {


    Fragment products;
    Fragment orders;
    Fragment create;

    User user;
    Store store;
    List<Pair<String, Product>> productsList;
    // List<Pair<String, >>
    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        Intent intent = getIntent();
        user = intent.getSerializableExtra("user", User.class);
        setContentView(R.layout.store_owner_layout);

        orders = new OwnerOrders();
        create = new CreateProduct(user.getStoreName());
        updateProducts();

        BottomNavigationView nav = findViewById(R.id.storeOwnerNav);

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.myproducts) {
                    updateProducts();
                    return true;
                }
                else if (item.getItemId() == R.id.ownerorders) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.ownerContainer, orders).commit();
                    return true;
                }
                else if (item.getItemId() == R.id.createproduct) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.ownerContainer, create).commit();
                    return true;
                }
                return false;
            }
        });

        getSupportFragmentManager().setFragmentResultListener("newProduct", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                Product p = result.getSerializable("product", Product.class);
                DatabaseReference db = FirebaseDatabase.getInstance().getReference();
                String pid = UUID.randomUUID().toString();
                store.addProduct(pid);
                db.child("products").child(pid).setValue(p);
                db.child("stores").child(user.getStoreName()).setValue(store);
                updateProducts();
            }
        });

        getSupportFragmentManager().setFragmentResultListener("editProduct", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                Product p = result.getSerializable("product", Product.class);
                FirebaseDatabase.getInstance().getReference("products").child(result.getString("pid")).setValue(p);
                updateProducts();
            }
        });

        getSupportFragmentManager().setFragmentResultListener("delProduct", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                store.getProducts().remove(result.getString("pid"));
                FirebaseDatabase.getInstance().getReference("products").child(result.getString("pid")).removeValue();
                FirebaseDatabase.getInstance().getReference("stores").child(user.getStoreName()).setValue(store);
                updateProducts();
            }
        });
    }

    public void updateProducts() {
        FirebaseDatabase.getInstance().getReference().child("stores").child(user.getStoreName()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                store = task.getResult().getValue(Store.class);
                productsList=new ArrayList<>();
                Query ref = FirebaseDatabase.getInstance().getReference("products").orderByChild("storeName").equalTo(user.getStoreName());
                ref.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                    @Override
                    public void onSuccess(DataSnapshot t) {
                        for (DataSnapshot ds : t.getChildren()) {
                            productsList.add(new Pair<>(ds.getKey(), ds.getValue(Product.class)));
                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.ownerContainer, new OwnerProducts(productsList)).commit();
                    }
                });
            }
        });
    }
}
