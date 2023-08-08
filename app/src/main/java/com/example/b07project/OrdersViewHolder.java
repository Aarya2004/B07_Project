package com.example.b07project;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

public class OrdersViewHolder extends RecyclerView.ViewHolder {

    TextView shopperName;
    TextView productName;
    TextView quantity;
    SwitchCompat fulfilled;
    static Boolean isTouched = false;

    @SuppressLint("ClickableViewAccessibility")
    public OrdersViewHolder(@NonNull View itemView) {
        super(itemView);
        shopperName = itemView.findViewById(R.id.shopperName);
        productName = itemView.findViewById(R.id.productName);
        fulfilled = itemView.findViewById(R.id.fulfilledSwitch);
        fulfilled.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                isTouched = true;
                return false;
            }
        });
        fulfilled.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isTouched) {
                    isTouched = false;
                    Log.v("demo", "Position Checked: "+getLayoutPosition());
                    StoreOwnerOrders.updateDatabase(getLayoutPosition(), isChecked);
                }
            }
        });
    }
}

