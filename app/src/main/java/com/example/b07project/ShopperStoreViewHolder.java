package com.example.b07project;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ShopperStoreViewHolder extends RecyclerView.ViewHolder {

    TextView storeName;

    public ShopperStoreViewHolder(@NonNull View itemView) {
        super(itemView);
        storeName = itemView.findViewById(R.id.storeName);
    }
}
