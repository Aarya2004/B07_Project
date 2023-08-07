package com.example.b07projectself;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateProduct#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateProduct extends Fragment {

    String storeName;
    public CreateProduct() {
        // Required empty public constructor
    }
    public CreateProduct(String store) {
        storeName=store;
    }

    public static CreateProduct newInstance() {
        CreateProduct fragment = new CreateProduct();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_createproduct, container, false);
        EditText name = view.findViewById(R.id.getName);
        EditText brand = view.findViewById(R.id.getBrand);
        EditText price = view.findViewById(R.id.getPrice);
        EditText desc = view.findViewById(R.id.getDescription);
        Button toCreate = view.findViewById(R.id.createProductButton);
        toCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.length() == 0 || brand.length() == 0 || price.length() == 0 || desc.length() == 0) return;
                Bundle result = new Bundle();
                result.putSerializable("product", new Product(name.getText().toString(), brand.getText().toString(), Double.parseDouble(price.getText().toString()), desc.getText().toString(), storeName));
                getParentFragmentManager().setFragmentResult("newProduct", result);
            }
        });
        return view;
    }
}