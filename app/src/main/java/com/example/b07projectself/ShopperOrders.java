package com.example.b07projectself;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ShopperOrders extends Fragment {

    List<Pair<String, Order>> orders;
    public ShopperOrders() {
        // Required empty public constructor
    }
    public ShopperOrders(List<Pair<String, Order>> l) {orders = l;}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopper_orders, container, false);

        RecyclerView rv = view.findViewById(R.id.shopperOrdersList);
        rv.setAdapter(new ShopperOrderListAdapter(orders, this));
        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        return view;
    }

    public void deleteOrder() {
        getParentFragmentManager().setFragmentResult("delOrder", new Bundle());
    }
}