package com.example.b07projectself;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.text.Layout;
import android.util.Pair;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.LayoutInflaterFactory;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.function.Function;

public class OwnerProductListAdapter extends RecyclerView.Adapter<OwnerProductListAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView price;
        public Button edit;

        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.productName);
            price = itemView.findViewById(R.id.productPrice);
            edit = itemView.findViewById(R.id.ownerProductEditButton);
        }
    }

    public List<Pair<String, Product>> products;
    OwnerProducts frag;
    public OwnerProductListAdapter(List<Pair<String, Product>> prods, OwnerProducts f) {
        products=prods;
        frag = f;
    }

    @Override
    public OwnerProductListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View newView = inflater.inflate(R.layout.product_list_item, parent, false);
        return new ViewHolder(newView);
    }

    @Override
    public void onBindViewHolder(OwnerProductListAdapter.ViewHolder holder, int position) {
        if (products == null) return;
        Product p = products.get(holder.getAdapterPosition()).second;
        holder.name.setText(p.getName());
        holder.price.setText("$"+Double.toString(p.getPrice()));
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View v = View.inflate(view.getContext(), R.layout.edit_product_popup, null);
                EditText name = v.findViewById(R.id.editProductName);
                EditText brand = v.findViewById(R.id.editProductBrand);
                EditText price = v.findViewById(R.id.editProductPrice);
                EditText desc = v.findViewById(R.id.editProductDescription);
                Button cancel = v.findViewById(R.id.editProductCancel);
                Button done = v.findViewById(R.id.editProductDone);
                Button delete = v.findViewById(R.id.editProductDelete);
                name.setText(p.getName());
                brand.setText(p.getBrand());
                price.setText(Double.toString(p.getPrice()));
                desc.setText(p.getDesc());
                PopupWindow pop = new PopupWindow(v, ConstraintLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                pop.showAtLocation(view, Gravity.CENTER, 0, 0);

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        pop.dismiss();
                    }
                });
                done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Product x = products.get(holder.getAdapterPosition()).second;
                        x.setBrand(brand.getText().toString());
                        x.setDesc(desc.getText().toString());
                        x.setName(name.getText().toString());
                        x.setPrice(Double.parseDouble(price.getText().toString()));
                        frag.editProduct(products.get(holder.getAdapterPosition()).first, x);
                        pop.dismiss();
                    }
                });
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        frag.delProduct(products.get(holder.getAdapterPosition()).first, products.get(holder.getAdapterPosition()).second);
                        pop.dismiss();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
