package com.example.b07projectself;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class BigProduct extends Fragment {

    public BigProduct() {
        // Required empty public constructor
    }
    String pid;
    Product product;
    public BigProduct(String id, Product p) {
        pid = id;
        product = p;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_product_big, container, false);
        ((TextView)view.findViewById(R.id.bigProductName)).setText(product.getName());
        ((TextView)view.findViewById(R.id.bigProductPrice)).setText("$"+Double.toString(product.getPrice()));
        ((TextView)view.findViewById(R.id.bigProductbrand)).setText(product.getBrand());
        ((TextView)view.findViewById(R.id.bigProductDescription)).setText(product.getDesc());
        ((Button)view.findViewById(R.id.bigProductBack)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().setFragmentResult("back", new Bundle());
            }
        });
        TextView quantity = view.findViewById(R.id.quantity);
        ((Button)view.findViewById(R.id.addToCart)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b = new Bundle();
                b.putString("pid", pid);
                b.putSerializable("product", product);
                b.putInt("quantity", Integer.parseInt(quantity.getText().toString()));
                getParentFragmentManager().setFragmentResult("toCart", b);
            }
        });
        ((ImageView)view.findViewById(R.id.quantityUp)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int q = Integer.parseInt(quantity.getText().toString());
                quantity.setText(Integer.toString(q+1));
            }
        });
        ((ImageView)view.findViewById(R.id.quantityDown)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int q = Math.max(1, Integer.parseInt(quantity.getText().toString())-1);
                quantity.setText(Integer.toString(q));
            }
        });
        return view;

    }
}