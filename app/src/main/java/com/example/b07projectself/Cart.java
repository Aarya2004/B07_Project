package com.example.b07projectself;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

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

        RecyclerView rv = view.findViewById(R.id.cartItemList);
        rv.setAdapter(new CartListAdapter(orders, this));
        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        return view;
    }
    public void restart() {
        getParentFragmentManager().setFragmentResult("updateCart", new Bundle());
    }
}