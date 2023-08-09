package com.example.b07projectself;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

public class OwnerProducts extends Fragment {

    List<Pair<String, Product>> products;
    public OwnerProducts() {
    }
    public OwnerProducts(List<Pair<String, Product>> l) {
        products=l;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myproducts, container, false);

        ((TextView)view.findViewById(R.id.myproducts_label)).setText(products.isEmpty() ? "No Products Yet..." : "My Store");

        RecyclerView rv = view.findViewById(R.id.ownerProductList);

        OwnerProductListAdapter adapter = new OwnerProductListAdapter(products, this);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        return view;
    }

    public void editProduct(String pid, Product p) {
        Bundle b = new Bundle();
        b.putString("pid", pid);
        b.putSerializable("product", p);
        getParentFragmentManager().setFragmentResult("editProduct", b);
    }

    public void delProduct(String pid, Product p) {
        Bundle b = new Bundle();
        b.putString("pid", pid);
        b.putSerializable("product", p);
        getParentFragmentManager().setFragmentResult("delProduct", b);
    }
}