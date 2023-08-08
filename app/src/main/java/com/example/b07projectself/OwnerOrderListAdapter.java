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

public class OwnerOrderListAdapter extends RecyclerView.Adapter<OwnerOrderListAdapter.ViewHolder> {

    public List<Pair<String, Order>> orders;
    OwnerOrders frag;

    public OwnerOrderListAdapter(List<Pair<String, Order>> s, OwnerOrders f) {
        orders = s;
        frag = f;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View newView = inflater.inflate(R.layout.owner_order_list_item, parent, false);
        return new ViewHolder(newView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (orders == null) return;
        holder.productName.setText(orders.get(position).second.getProductName());
        String txt = "Total: $" + Double.toString(orders.get(position).second.getPrice()*orders.get(position).second.getQuantity());
        holder.productPrice.setText(txt);
        holder.productQuantity.setText(Integer.toString(orders.get(position).second.getQuantity()));
        holder.completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference("orders").child(orders.get(holder.getAdapterPosition()).first).child("completed").setValue(true);
                frag.update();
            }
        });

    }

    @Override
    public int getItemCount() {
        if(orders!=null) return orders.size(); else return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView productName;
        TextView productQuantity;
        TextView productPrice;
        Button completeButton;

        public ViewHolder(View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.ownerOrderName);
            productPrice = itemView.findViewById(R.id.ownerOrderPrice);
            productQuantity = itemView.findViewById(R.id.ownerOrderQuantity);
            completeButton = itemView.findViewById(R.id.orderComplete);
        }
    }


}