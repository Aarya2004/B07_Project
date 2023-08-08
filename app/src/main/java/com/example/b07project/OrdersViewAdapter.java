package com.example.b07project;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
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
        holder.shopperName.setText(ordersList.get(position).getShopperName()+"'s Order");
        holder.productName.setText(generateProductString(ordersList, position));
        holder.fulfilled.setText(R.string.fulfilledText);
        holder.fulfilled.setChecked(ordersList.get(position).isFulfilled());
    }

    private String generateProductString(List<Order> orders, int position) {
        String return_string = "";
        for (int i = 0; i < orders.get(position).getProducts().size(); i++) {
            return_string += orders.get(position).getProducts().get(i).getProductName() + " x" + orders.get(position).getProductQuantities().get(i) + "\n";
        }
        return_string = return_string.substring(0, return_string.length() - 1);
        return return_string;
    }

    @Override
    public int getItemCount() {
        return ordersList.size();
    }
}
