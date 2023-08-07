package com.example.b07projectself;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OwnerProducts extends Fragment {

    List<Pair<String, Product>> products;
    Store store;
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