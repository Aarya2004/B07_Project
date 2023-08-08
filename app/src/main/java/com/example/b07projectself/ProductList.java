package com.example.b07projectself;

import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductList extends Fragment {
    List<Pair<String, Product>> products;

    public ProductList(){}

    public ProductList(List<Pair<String, Product>> l ) { products = l;}

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle b) {
        View view = inflater.inflate(R.layout.shopper_product_list, container, false);
        ((Button)view.findViewById(R.id.productsBack)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().setFragmentResult("back", new Bundle());
            }
        });
        RecyclerView rv = view.findViewById(R.id.shopperProductList);
        rv.setAdapter(new ProductListAdapter(products, this));
        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        return view;
    }
    public void viewProduct(String s, Product p) {
        Bundle b = new Bundle();
        b.putString("pid", s);
        b.putSerializable("product", p);
        getParentFragmentManager().setFragmentResult("viewProduct", b);
    }
}
