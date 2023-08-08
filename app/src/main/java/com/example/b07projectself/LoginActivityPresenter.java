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
        if (checkInput(emailAddress) || checkInput(password)) return;
        model.modelLogin(currActivity, this, emailAddress, password);
    }

    public void presenterRegisterShopper(Activity currActivity, EditText emailAddress, EditText password){
        if (checkInput(emailAddress) || checkInput(password)) return;
        model.modelRegisterShopper(currActivity, this, emailAddress, password);
    }

    public void presenterRegisterOwner(Activity currActivity, DatabaseReference db, EditText emailAddress, EditText password, EditText name, PopupWindow pop){
        if (checkInput(name)) return;
        model.modelRegisterOwner(currActivity,this, db, emailAddress, password, name, pop);
    }

    public void displayToast(String message) { view.displayToast(message); }

    public void toApp(User user) { view.toApp(user); }

    public boolean checkInput(EditText input) {
        return input.length() == 0;
    }
}
