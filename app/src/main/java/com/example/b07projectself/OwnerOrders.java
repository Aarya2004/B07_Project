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

public class OwnerOrders extends Fragment {

    List<Pair<String, Order>> orders;
    public OwnerOrders() {
        // Required empty public constructor
    }

    public OwnerOrders(List<Pair<String, Order>> l) {
        orders = l;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ownerorders, container, false);

        RecyclerView rv = view.findViewById(R.id.ownerOrderList);
        rv.setAdapter(new OwnerOrderListAdapter(orders, this));
        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        return view;
    }

    public void update() {
        getParentFragmentManager().setFragmentResult("completeOrder", new Bundle());
    }
}