package com.example.b07project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class ShopperStoreViewAdapter extends RecyclerView.Adapter<ShopperStoreViewHolder> {

    Context context;
    List<Store> storesList;

    public ShopperStoreViewAdapter(Context context, List<Store> storesList) {
        this.context = context;
        this.storesList = storesList;
    }

    @NonNull
    @Override
    public ShopperStoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ShopperStoreViewHolder(LayoutInflater.from(context).inflate(R.layout.shopper_store_view, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ShopperStoreViewHolder holder, int position) {
        Store store = storesList.get(position);
        holder.storeName.setText(store.getStoreName());

//        holder.itemView.setOnClickListener(v -> {
//            ((ShopperStores) context).createStoreLayout(store);
//        });
    }

    @Override
    public int getItemCount() {
        return storesList.size();
    }
}
