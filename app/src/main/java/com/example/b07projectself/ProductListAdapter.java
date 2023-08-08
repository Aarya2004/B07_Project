package com.example.b07projectself;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    public List<Pair<String, Product>> products;
    ProductList frag;

    public ProductListAdapter(List<Pair<String, Product>> s, ProductList f) {
        products = s;
        frag = f;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View newView = inflater.inflate(R.layout.shopper_product_list_item, parent, false);
        return new ViewHolder(newView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (products == null) return;
        holder.productName.setText(products.get(position).second.getName());
        holder.productPrice.setText(Double.toString(products.get(holder.getAdapterPosition()).second.getPrice()));
        holder.goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frag.viewProduct(products.get(holder.getAdapterPosition()).first, products.get(holder.getAdapterPosition()).second);
            }
        });

    }

    @Override
    public int getItemCount() {
        if(products!=null) return products.size(); else return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView productName;
        TextView productPrice;
        Button goButton;

        public ViewHolder(View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productListName);
            productPrice = itemView.findViewById(R.id.productListPrice);
            goButton = itemView.findViewById(R.id.viewProduct);
        }
    }


}