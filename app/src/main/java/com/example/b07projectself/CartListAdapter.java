package com.example.b07projectself;

import android.content.Context;
import android.util.Pair;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {

    public List<Order> orders;
    Cart frag;

    public CartListAdapter(List<Order> s, Cart f) {
        orders = s;
        frag = f;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View newView = inflater.inflate(R.layout.cart_item, parent, false);
        return new ViewHolder(newView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (orders == null) return;
        holder.productName.setText(orders.get(position).getProductName());
        String txt = "Total Price: $" + Double.toString(orders.get(position).getPrice()*orders.get(position).getQuantity());
        holder.productPrice.setText(txt);
        holder.productEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View v = View.inflate(view.getContext(), R.layout.quantity_select, null);
                EditText quantity = v.findViewById(R.id.quantitySelect);
                quantity.setText(Integer.toString(orders.get(holder.getAdapterPosition()).getQuantity()));
                ImageView minus = v.findViewById(R.id.quantitySelectDown);
                ImageView plus = v.findViewById(R.id.quantitySelectUp);
                Button done = v.findViewById(R.id.quantitySelectDone);
                PopupWindow pop = new PopupWindow(v, ConstraintLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                pop.showAtLocation(view, Gravity.CENTER, 0, 0);
                minus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        quantity.setText(Integer.toString(Math.max(1, Integer.parseInt(quantity.getText().toString()) - 1)));
                    }
                });
                plus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        quantity.setText(Integer.toString(Integer.parseInt(quantity.getText().toString()) + 1));
                    }
                });
                done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (quantity.length() == 0 || Integer.parseInt(quantity.getText().toString()) == 0) return;
                        orders.get(holder.getAdapterPosition()).setQuantity(Integer.parseInt(quantity.getText().toString()));
                        pop.dismiss();
                        frag.restart();
                    }
                });
            }
        });

        holder.productDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orders.remove(holder.getAdapterPosition());
                frag.restart();
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
        Button productEdit;
        Button productDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.cartProductName);
            productPrice = itemView.findViewById(R.id.cartProductPrice);
            productEdit = itemView.findViewById(R.id.cartProductEdit);
            productDelete = itemView.findViewById(R.id.cartProductDelete);
        }
    }


}