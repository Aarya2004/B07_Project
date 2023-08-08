package com.example.b07projectself;

import android.app.Activity;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivityModel {
    private FirebaseAuth mAuth;

    public LoginActivityModel(){
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) mAuth.signOut();
    }

    public void modelLogin(Activity currActivity, LoginActivityPresenter presenter, EditText emailAddress, EditText password){
        mAuth.signInWithEmailAndPassword(emailAddress.getText().toString(), password.getText().toString())
                .addOnCompleteListener(currActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseDatabase.getInstance().getReference().child("users").child(mAuth.getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DataSnapshot> task) {
                                    if (!task.isSuccessful()) {
                                        mAuth.getCurrentUser().delete();
                                        return;
                                    }
                                    User u = task.getResult().getValue(User.class);
                                    presenter.toApp(u);
                                }
                            });
                        } else {
                            // If sign in fails, display a message to the user.
                            presenter.displayToast("Authentication failed.");
                        }
                    }
                });
    }

    public void modelRegisterShopper(Activity currActivity, LoginActivityPresenter presenter, EditText emailAddress, EditText password){
        mAuth.createUserWithEmailAndPassword(emailAddress.getText().toString(), password.getText().toString())
                .addOnCompleteListener(currActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser u = mAuth.getCurrentUser();
                            User user = new User();
                            user.setStoreOwner(false);
                            FirebaseDatabase.getInstance().getReference().child("users").child(u.getUid()).setValue(user);
                            presenter.toApp(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            presenter.displayToast("Authentication failed.");
                        }
                    }
                });
    }

    public void modelRegisterOwner(Activity currActivity, LoginActivityPresenter presenter, DatabaseReference db, EditText emailAddress, EditText password, EditText name, PopupWindow pop){
        db.child("stores").child(name.getText().toString()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.getResult().exists()) {
                    presenter.displayToast("Store already exists");
                    pop.setFocusable(true); return;
                }
                mAuth.createUserWithEmailAndPassword(emailAddress.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(currActivity, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser u = mAuth.getCurrentUser();
                                    User user = new User();
                                    user.setStoreOwner(true);
                                    user.setStoreName(name.getText().toString());
                                    db.child("users").child(u.getUid()).setValue(user);
                                    Store store = new Store(); store.setOwner(u.getUid());
                                    db.child("stores").child(name.getText().toString()).setValue(store);
                                    presenter.toApp(user);
                                } else {
                                    pop.setFocusable(true);
                                    pop.dismiss();
                                    presenter.displayToast(task.getException().getMessage());
                                }
                            }
                        });
            }
        });
    }
}
