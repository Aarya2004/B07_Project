package com.example.b07projectself;

import android.app.Activity;
import android.widget.EditText;
import android.widget.PopupWindow;
import com.google.firebase.database.DatabaseReference;


public class LoginActivityPresenter {

    LoginActivityModel model;
    LoginActivityView view;

    public LoginActivityPresenter(LoginActivityView view, LoginActivityModel model) {
        this.model = model;
        this.view = view;
    }

    public void presenterLogin(Activity currActivity, EditText emailAddress, EditText password) {
        if (emailAddress.length() == 0 || password.length() == 0) return;
        model.modelLogin(currActivity, this, emailAddress, password);
    }

    public void presenterRegisterShopper(Activity currActivity, EditText emailAddress, EditText password){
        if (emailAddress.length() == 0 || password.length() == 0) return;
        model.modelRegisterShopper(currActivity, this, emailAddress, password);
    }

    public void presenterRegisterOwner(Activity currActivity, DatabaseReference db, EditText emailAddress, EditText password, EditText name, PopupWindow pop){
        if (name.length() == 0) return;
        model.modelRegisterOwner(currActivity,this, db, emailAddress, password, name, pop);
    }

    public void displayToast(String message) { view.displayToast(message); }

    public void toApp(User user) { view.toApp(user); }
}
