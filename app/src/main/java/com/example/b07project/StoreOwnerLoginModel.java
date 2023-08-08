package com.example.b07project;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StoreOwnerLoginModel {
    FirebaseDatabase db;

    public StoreOwnerLoginModel(){
        db = FirebaseDatabase.getInstance("https://b07-project-45a16-default-rtdb.firebaseio.com/");
    }

    public void queryDB(StoreOwnerLoginPresenter presenter, String id){
        DatabaseReference ref= db.getReference();
        DatabaseReference query = ref.child("Owners").child(id);

        query.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                String password = null;
                String storeName = null;
                if(!snapshot.exists()){
                    presenter.user_does_not_exist();
                    return;
                }
                for(DataSnapshot child: snapshot.getChildren()){
                    if(String.valueOf(child.getKey()).equals("password")){
                        password = String.valueOf(child.getValue());
                    }
                    if(String.valueOf(child.getKey()).equals("Store Name")){
                        storeName = String.valueOf(child.getValue());
                    }
                }
                boolean user_confirmed = presenter.confirm_user(password, storeName);
                if(user_confirmed){
                    presenter.view.user_confirmed(storeName);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }
}
