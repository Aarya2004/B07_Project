package com.example.b07projectself;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ShopperActivity extends AppCompatActivity {

    Fragment shop;
    Fragment cart;
    Fragment orders;
    BottomNavigationView nav;
    Stack<Fragment> shopStack;
    List<Order> cartList;
    List<Pair<String, Order>> sentOrders;

    User user;
    List<Pair<String, Store>> stores;

    public void onCreate(Bundle b) {
        super.onCreate(b);
        Intent intent = getIntent();
        user = intent.getSerializableExtra("user", User.class);
        setContentView(R.layout.shopper_layout);

        nav = findViewById(R.id.shopperNav);

        shopStack = new Stack<>();
        cartList=new ArrayList<>();
        updateOrders(false);
        updateStores();
        cart = new Cart(cartList);
        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.shop) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.shopperContainer, shop).commit();
                    return true;
                } else if (item.getItemId() == R.id.cart) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.shopperContainer, cart).commit();
                    return true;
                } else if (item.getItemId() == R.id.orders) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.shopperContainer, orders).commit();
                    return true;
                }
                return false;
            }
        });

        getSupportFragmentManager().setFragmentResultListener("back", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                shop = shopStack.pop();
                getSupportFragmentManager().beginTransaction().replace(R.id.shopperContainer, shop).commit();
            }
        });
        getSupportFragmentManager().setFragmentResultListener("gotoProduct", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String storeName = result.getString("storeName");
                Store s = result.getSerializable("store", Store.class);
                Query q = FirebaseDatabase.getInstance().getReference("products").orderByChild("storeName").equalTo(storeName);
                q.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                    @Override
                    public void onSuccess(DataSnapshot dataSnapshot) {
                        List<Pair<String, Product>> l = new ArrayList<>();
                        for (DataSnapshot d : dataSnapshot.getChildren()) {
                            l.add(new Pair<>(d.getKey(), d.getValue(Product.class)));
                        }
                        shopStack.push(shop);
                        shop = new ProductList(l);
                        getSupportFragmentManager().beginTransaction().replace(R.id.shopperContainer, shop).commit();
                    }
                });
            }
        });

        getSupportFragmentManager().setFragmentResultListener("viewProduct", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String pid = result.getString("pid");
                Product p = result.getSerializable("product", Product.class);
                shopStack.push(shop);
                shop = new BigProduct(pid, p);
                getSupportFragmentManager().beginTransaction().replace(R.id.shopperContainer, shop).commit();
            }
        });

        getSupportFragmentManager().setFragmentResultListener("toCart", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                Product p = result.getSerializable("product", Product.class);
                String pid = result.getString("pid");
                int quantity = result.getInt("quantity");
                Order o = new Order(pid, p.getName(), quantity, p.getPrice(), p.getStoreName(), FirebaseAuth.getInstance().getCurrentUser().getUid(), false);
                cartList.add(o);
                updateCart(true);
            }
        });
        getSupportFragmentManager().setFragmentResultListener("updateCart", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                updateCart(false);
            }
        });

        getSupportFragmentManager().setFragmentResultListener("order", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                cartList = new ArrayList<>();
                updateCart(false);
                updateOrders(true);
            }
        });

        getSupportFragmentManager().setFragmentResultListener("delOrder", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                updateOrders(true);
            }
        });
    }

    public void updateStores() {
        FirebaseDatabase.getInstance().getReference("stores").get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                stores = new ArrayList<>();
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    stores.add(new Pair<>(d.getKey(), d.getValue(Store.class)));
                }
                if(shop!=null) getSupportFragmentManager().beginTransaction().remove(shop).commit();
                shop = new StoreList(stores);
                nav.setSelectedItemId(R.id.shop);
            }
        });
    }
    public void updateCart(boolean replace) {
        cart = new Cart(cartList);
        if(replace) nav.setSelectedItemId(R.id.cart);
    }

    public void updateOrders(boolean replace) {
        Query q = FirebaseDatabase.getInstance().getReference("orders").orderByChild("shopper").equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());
        q.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                sentOrders = new ArrayList<>();
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    sentOrders.add(new Pair<>(d.getKey(), d.getValue(Order.class)));
                }
                if(orders!= null) getSupportFragmentManager().beginTransaction().remove(orders).commit();
                orders = new ShopperOrders(sentOrders);
                if(replace) nav.setSelectedItemId(R.id.orders);
            }
        });
    }
}
