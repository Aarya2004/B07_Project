package com.example.b07project;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ShopperOrdersViewHolder extends RecyclerView.ViewHolder {

    TextView shopperName;
    TextView productName;
    TextView fulfilled;

    public ShopperOrdersViewHolder(@NonNull View itemView) {
        super(itemView);
        shopperName = itemView.findViewById(R.id.shopperOrderName);
        productName = itemView.findViewById(R.id.shopperOrderProductName);
        fulfilled = itemView.findViewById(R.id.shopperOrderFulfilledStatus);
    }
}
