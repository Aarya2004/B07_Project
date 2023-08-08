package com.example.b07projectself;

import android.os.Bundle;

import android.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class StoreList extends Fragment {

    List<Pair<String, Store>> stores;
    public StoreList() {
        // Required empty public constructor
    }
    public StoreList(List<Pair<String, Store>> s) {stores = s;}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store_list, container, false);
        RecyclerView rv = view.findViewById(R.id.shopsList);
        StoreListAdapter adapter = new StoreListAdapter(stores, this);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        return view;
    }

    public void gotoStore(String storename, Store s) {
        Bundle b = new Bundle();
        b.putString("storeName", storename);
        b.putSerializable("store", s);
        getParentFragmentManager().setFragmentResult("gotoProduct", b);
    }
}