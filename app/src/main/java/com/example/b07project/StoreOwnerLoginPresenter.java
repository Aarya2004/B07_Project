package com.example.b07project;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class StoreOwnerLoginPresenter {

    StoreOwnerLoginModel model;
    StoreOwnerLoginView view;
    String userPass;

    public StoreOwnerLoginPresenter(StoreOwnerLoginView view, StoreOwnerLoginModel model) {
        this.model = model;
        this.view = view;
    }

    public void check_user_exists(String username, String password, String id) {
        if (username.equals("") || password.equals(""))
        {
            view.displayToast("Username/Password cannot be empty!");
        }
        else
        {
            userPass = password;
            model.queryDB(this, id);
        }
    }

    public boolean confirm_user(String password, String storeName){
        if(!password.equals(userPass)){
            view.displayToast("Incorrect password!");
            return false;
        }else{
            return true;
        }
    }

    public void user_does_not_exist(){
        view.displayToast("No such user exists!");
    }
}
