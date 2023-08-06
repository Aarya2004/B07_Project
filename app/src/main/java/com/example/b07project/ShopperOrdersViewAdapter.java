package com.example.b07project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class ShopperOrdersViewAdapter extends RecyclerView.Adapter<ShopperOrdersViewHolder>{

    Context context;
    List<Order> ordersList;

    public ShopperOrdersViewAdapter(Context context, List<Order> ordersList) {
        this.context = context;
        this.ordersList = ordersList;
    }

    @NonNull
    @Override
    public ShopperOrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ShopperOrdersViewHolder(LayoutInflater.from(context).inflate(R.layout.shopper_orders, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ShopperOrdersViewHolder holder, int position) {

        holder.productName.setText(ordersList.get(position).getProductName());
        holder.quantity.setText(String.format(Locale.getDefault(), "%d", ordersList.get(position).getQuantity()));
        holder.fulfilled.setChecked(ordersList.get(position).isFulfilled());
    }

    @Override
    public int getItemCount() {
        return ordersList.size();
    }

}


