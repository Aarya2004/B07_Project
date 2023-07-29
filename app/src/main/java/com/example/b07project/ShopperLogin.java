package com.example.b07project;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ShopperLogin extends AppCompatActivity{

    TextInputEditText editTextEmail;
    TextInputEditText editTextPassword;
    Button buttonLogin;
    FirebaseAuth mAuth;
    ProgressBar progressBarShopper;
    TextView textViewShopper;

    @Override
    public void onStart() {
        //check if user is already logged in. If yes open main activity
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopper_login);

        mAuth = FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.shopperEmail);
        editTextPassword = findViewById(R.id.shopperPassword);
        buttonLogin = findViewById(R.id.button_loginShopper);
        progressBarShopper = findViewById(R.id.progressBarShopper);
        textViewShopper = findViewById(R.id.RegisterNow);
        textViewShopper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //to open the login activity intent
                Intent intent = new Intent(getApplicationContext(), ShopperRegister.class);
                startActivity(intent);
                finish();
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBarShopper.setVisibility(View.VISIBLE);
                String emailShopper;
                String passwordShopper;

                emailShopper = String.valueOf(editTextEmail.getText());
                passwordShopper = String.valueOf(editTextPassword.getText());

                //check empty email or password
                if (TextUtils.isEmpty(emailShopper)){
                    Toast.makeText(ShopperLogin.this, "Must enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(passwordShopper)){
                    Toast.makeText(ShopperLogin.this, "Must enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                //call sign in with email and pass using firebase doc

                mAuth.signInWithEmailAndPassword(emailShopper, passwordShopper)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBarShopper.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    finish();

                                } else {

                                    Toast.makeText(ShopperLogin.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });


            }
        });
    }







}











