package com.example.b07project;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
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

public class ShopperRegister extends AppCompatActivity {
    TextInputEditText editTextEmail;
    TextInputEditText editTextPassword;
    Button buttonRegister;
    FirebaseAuth mAuth;
    ProgressBar progressBarShopper;
    TextView textViewShopper;
    Button ownerRegisterSwitch;


//
//    @Override
//    public void onStart() {
//        //check if user is already logged in. If yes open main activity
//        super.onStart();
//
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser != null){
//            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//            startActivity(intent);
//            finish();
//        }
//    }



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopper_register);
        mAuth = FirebaseAuth.getInstance();
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

                emailShopper = String.valueOf(editTextEmail.getText());
                passwordShopper = String.valueOf(editTextPassword.getText());

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

                mAuth.createUserWithEmailAndPassword(emailShopper, passwordShopper)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBarShopper.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(ShopperRegister.this, "Account created",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), ShopperLogin.class);
                                    startActivity(intent);
                                    finish();


                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(ShopperRegister.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });


    }


}
