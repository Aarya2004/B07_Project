package com.example.b07project;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

public class ShopperOrdersViewHolder extends RecyclerView.ViewHolder{
    TextView orderID;
    TextView productName;
    TextView quantity;
    SwitchCompat fulfilled;

    public ShopperOrdersViewHolder(@NonNull View itemView) {
        super(itemView);
        orderID = itemView.findViewById(R.id.orderID);
        productName = itemView.findViewById(R.id.shopperProductName);
        quantity = itemView.findViewById(R.id.shopperQuantity);

    }

}


