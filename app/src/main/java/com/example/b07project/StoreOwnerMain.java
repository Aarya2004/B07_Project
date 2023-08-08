package com.example.b07project;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StoreOwnerMain {
    static StoreOwner owner;
    static Store store;

    FirebaseDatabase db;

    public StoreOwnerMain(StoreOwner owner){
        if(this.owner == null){
            this.owner = owner;
        }
        if(store == null){
            store = new Store(owner);;
        }
        getProducts();
    }

    public static StoreOwner ownerLoggedIn(){
        return owner;
    }

    public Store storeLoggedIn(){ return store; }

    public static void ownerLoggedout(){
        owner = null;
        store = null;
    }

    public void getProducts(){
        DatabaseReference ref= db.getReference();
        DatabaseReference query = ref.child("Owners").child(owner.getId()).child("products");

        query.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if(!snapshot.exists()) return;
                for(DataSnapshot child: snapshot.getChildren()){
                    String productId = String.valueOf(child.getKey());
                    String productName = String.valueOf(child.child(productId).child("productName"));
                    int price = Integer.parseInt(String.valueOf(child.child(productId).child("price")));
                    Product product = new Product(productId, productName, price);
                    store.addProduct(product);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }
}
