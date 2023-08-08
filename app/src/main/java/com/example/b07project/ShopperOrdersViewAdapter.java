package com.example.b07project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ShopperOrdersViewAdapter extends RecyclerView.Adapter<ShopperOrdersViewHolder> {

    Context context;
    List<Order> ordersList;

    public ShopperOrdersViewAdapter(Context context, List<Order> ordersList) {
        this.context = context;
        this.ordersList = ordersList;
    }

    @NonNull
    @Override
    public ShopperOrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ShopperOrdersViewHolder(LayoutInflater.from(context).inflate(R.layout.item_shopper, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ShopperOrdersViewHolder holder, int position) {
        holder.shopperName.setText(ordersList.get(position).getShopperName()+"'s Order");
        holder.productName.setText(generateProductString(ordersList, position));
        holder.fulfilled.setText(ordersList.get(position).isFulfilled() ? "Order Fulfilled" : "Order In Progress");
    }

    private String generateProductString(List<Order> orders, int position) {
        StringBuilder return_string = new StringBuilder();
        for (int i = 0; i < orders.get(position).getProducts().size(); i++) {
            return_string.append(orders.get(position).getProducts().get(i).getProductName()).append(" x").append(orders.get(position).getProductQuantities().get(i)).append("\n");
        }
        if(return_string.length() > 0) {
            return_string = new StringBuilder(return_string.substring(0, return_string.length() - 1));
        }
        return return_string.toString();
    }

    @Override
    public int getItemCount() {
        return ordersList.size();
    }
}
