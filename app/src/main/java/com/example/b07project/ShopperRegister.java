package com.example.b07project;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ShopperRegister extends AppCompatActivity {
    TextInputEditText editTextEmail;
    TextInputEditText editTextPassword;
    Button buttonRegister;
    FirebaseDatabase firedb;
    ProgressBar progressBarShopper;
    TextView textViewShopper;
    Button ownerRegisterSwitch;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        firedb = FirebaseDatabase.getInstance("https://b07-project-45a16-default-rtdb.firebaseio.com/");
        setContentView(R.layout.shopper_register);
        editTextEmail = findViewById(R.id.shopperEmail);
        editTextPassword = findViewById(R.id.shopperPassword);
        buttonRegister = findViewById(R.id.button_registerShopper);
        progressBarShopper = findViewById(R.id.progressBarShopper);
        ownerRegisterSwitch = findViewById(R.id.ownerSwitch);
        textViewShopper = findViewById(R.id.loginNow);
        textViewShopper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //to open the login activity intent
                Intent intent = new Intent(getApplicationContext(), ShopperLogin.class);
                startActivity(intent);
                finish();
            }
        });

        ownerRegisterSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StoreOwnerLogin.class);
                startActivity(intent);
                finish();
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBarShopper.setVisibility(View.VISIBLE);
                String emailShopper;
                String passwordShopper;
                String idShopper;

                emailShopper = String.valueOf(editTextEmail.getText());
                passwordShopper = String.valueOf(editTextPassword.getText());
                idShopper = String.valueOf(emailShopper.hashCode());

                //check empty email or password
                if (TextUtils.isEmpty(emailShopper)){
                    Toast.makeText(ShopperRegister.this, "Must enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(passwordShopper)){
                    Toast.makeText(ShopperRegister.this, "Must enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                //implement create user
                enter_into_db(idShopper, emailShopper, passwordShopper);
                Intent intent = new Intent(ShopperRegister.this, ShopperLogin.class);
                startActivity(intent);
                finish();
            }
        });


    }

    public void enter_into_db(String idShopper, String emailShopper, String passwordShopper){
        DatabaseReference db = firedb.getReference();

        boolean userExists = user_exists(db, idShopper);
        if(userExists){
            Toast myToast = Toast.makeText(getApplicationContext(), "User Already Exists!", Toast.LENGTH_SHORT);
            myToast.show();
            return;
        }

        db.child("Shoppers").child(idShopper).child("email").setValue(emailShopper);
        db.child("Shoppers").child(idShopper).child("password").setValue(passwordShopper);
        db.child("Shoppers").child(idShopper).child("Orders");

    }

    public boolean user_exists(DatabaseReference db, String id){
        final boolean[] isFound = {false};
        db.child("Shoppers").orderByKey().get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                    Toast myToast = Toast.makeText(ShopperRegister.this, "Database Error!", Toast.LENGTH_SHORT);
                    myToast.show();
                } else {
                    for (DataSnapshot ds : task.getResult().getChildren()) {
                        String value = ds.getValue().toString();
                        if (value.equals(id)) {
                            isFound[0] = true;
                            break;
                        }
                    }
                }
            }
        });
        return isFound[0];
    }


}
