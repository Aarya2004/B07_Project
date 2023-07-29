package com.example.b07project;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OrdersViewHolder extends RecyclerView.ViewHolder {

    TextView shopperName;
    TextView productName;

    public OrdersViewHolder(@NonNull View itemView) {
        super(itemView);
        shopperName = itemView.findViewById(R.id.shopperName);
        productName = itemView.findViewById(R.id.productName);
    }
}

