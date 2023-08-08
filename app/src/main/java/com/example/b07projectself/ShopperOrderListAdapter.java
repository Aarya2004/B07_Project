package com.example.b07projectself;

import android.content.Context;
import android.graphics.Color;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ShopperOrderListAdapter extends RecyclerView.Adapter<ShopperOrderListAdapter.ViewHolder> {

    public List<Pair<String, Order>> orders;
    ShopperOrders frag;

    public ShopperOrderListAdapter(List<Pair<String, Order>> s, ShopperOrders f) {
        orders = s;
        frag = f;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View newView = inflater.inflate(R.layout.shopper_orders_list_item, parent, false);
        return new ViewHolder(newView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (orders == null) return;
        holder.productName.setText(orders.get(position).second.getProductName());
        String txt = "$" + Double.toString(orders.get(position).second.getPrice()*orders.get(position).second.getQuantity());
        holder.productPrice.setText(txt);
        if (orders.get(position).second.isCompleted()) {
            holder.orderCompleted.setText("Order Complete!");
            holder.orderCompleted.setTextColor(Color.GREEN);
            holder.cancelButton.setText("Remove");
        } else {
            holder.orderCompleted.setText("Not Complete");
            holder.orderCompleted.setTextColor(Color.RED);
        }
        holder.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference("orders").child(orders.get(holder.getAdapterPosition()).first).removeValue();
                frag.deleteOrder();
            }
        });

    }

    @Override
    public int getItemCount() {
        if(orders!=null) return orders.size(); else return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView productName;
        TextView productPrice;
        TextView orderCompleted;
        Button cancelButton;

        public ViewHolder(View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.shopperOrderName);
            productPrice = itemView.findViewById(R.id.shopperOrderPrice);
            orderCompleted = itemView.findViewById(R.id.shopperOrderCompleted);
            cancelButton = itemView.findViewById(R.id.shopperOrderCancel);
        }
    }


}