package com.example.b07project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
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
    }

    @Override
    public int getItemCount() {
        System.out.println("HERE");
        return ordersList.size();
    }
}
