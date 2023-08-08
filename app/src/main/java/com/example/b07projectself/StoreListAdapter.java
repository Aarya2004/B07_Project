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

public class StoreListAdapter extends RecyclerView.Adapter<StoreListAdapter.ViewHolder> {

    public List<Pair<String, Store>> stores;
    StoreList frag;

    public StoreListAdapter(List<Pair<String, Store>> s, StoreList f) {
        stores = s;
        frag = f;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View newView = inflater.inflate(R.layout.shop_list_item, parent, false);
        return new ViewHolder(newView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (stores == null) return;
        holder.storeName.setText(stores.get(position).first);

        holder.goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frag.gotoStore(stores.get(holder.getAdapterPosition()).first, stores.get(holder.getAdapterPosition()).second);
            }
        });

    }

    @Override
    public int getItemCount() {
        if(stores!=null) return stores.size(); else return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView storeName;
        Button goButton;

        public ViewHolder(View itemView) {
            super(itemView);
            storeName = itemView.findViewById(R.id.listStoreName);
            goButton = itemView.findViewById(R.id.gotoStore);
        }
    }


}
