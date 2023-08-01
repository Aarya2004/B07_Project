package com.example.b07project;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

public class OrdersViewHolder extends RecyclerView.ViewHolder {

    TextView shopperName;
    TextView productName;
    TextView quantity;
    SwitchCompat fulfilled;

    public OrdersViewHolder(@NonNull View itemView) {
        super(itemView);
        shopperName = itemView.findViewById(R.id.shopperName);
        productName = itemView.findViewById(R.id.productName);
        quantity = itemView.findViewById(R.id.quantity);
        fulfilled = itemView.findViewById(R.id.fulfilledSwitch);
    }
}

