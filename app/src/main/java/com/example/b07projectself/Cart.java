package com.example.b07projectself;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.UUID;

public class Cart extends Fragment {

    List<Order> orders;
    public Cart() {
        // Required empty public constructor
    }
    public Cart(List<Order> l) {
        orders = l;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        ((Button)view.findViewById(R.id.buttonPurchase)).setOnClickListener(new View.OnClickListener() {
            DatabaseReference d = FirebaseDatabase.getInstance().getReference("orders");
            @Override
            public void onClick(View view) {
                for (Order o : orders) {
                    d.child(UUID.randomUUID().toString()).setValue(o);
                }
                getParentFragmentManager().setFragmentResult("order", new Bundle());
            }
        });

        RecyclerView rv = view.findViewById(R.id.cartItemList);
        rv.setAdapter(new CartListAdapter(orders, this));
        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        return view;
    }
    public void restart() {
        getParentFragmentManager().setFragmentResult("updateCart", new Bundle());
    }
}