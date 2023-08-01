package com.example.b07project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class OrdersViewAdapter extends RecyclerView.Adapter<OrdersViewHolder> {

    Context context;
    List<Order> ordersList;

    public OrdersViewAdapter(Context context, List<Order> ordersList) {
        this.context = context;
        this.ordersList = ordersList;
    }

    @NonNull
    @Override
    public OrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrdersViewHolder(LayoutInflater.from(context).inflate(R.layout.orders_item_view, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersViewHolder holder, int position) {
        holder.shopperName.setText(ordersList.get(position).getShopperName());
        holder.productName.setText(ordersList.get(position).getProductName());
        holder.quantity.setText(String.format(Locale.getDefault(), "%d", ordersList.get(position).getQuantity()));
        holder.fulfilled.setText(R.string.fulfilledText);
        holder.fulfilled.setChecked(ordersList.get(position).isFulfilled());
    }

    @Override
    public int getItemCount() {
        return ordersList.size();
    }
}
