package com.example.b07projectself;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

    private List<String> orderIds;
    private String storeName;

    public boolean isStoreOwner() {
        return storeOwner;
    }

    public void setStoreOwner(boolean storeOwner) {
        this.storeOwner = storeOwner;
    }

    private boolean storeOwner;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public List<String> getOrderIds() {
        return orderIds;
    }
    public void setOrderIds(List<String> l) {
        orderIds = l;
    }
    public User() {
    }

}
