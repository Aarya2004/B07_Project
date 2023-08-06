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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
                String emailShopper = String.valueOf(editTextEmail.getText());
                String passwordShopper = String.valueOf(editTextPassword.getText());

                if (TextUtils.isEmpty(emailShopper) || TextUtils.isEmpty(passwordShopper)){
                    Toast.makeText(ShopperRegister.this, "Must enter Email and Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                registerUser(emailShopper, passwordShopper);
            }
        });
    }

    private void registerUser(String emailShopper, String passwordShopper) {
        DatabaseReference db = firedb.getReference("Shoppers");
        db.orderByChild("email").equalTo(emailShopper).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Toast.makeText(ShopperRegister.this, "User with this email already exists", Toast.LENGTH_SHORT).show();
                    progressBarShopper.setVisibility(View.GONE);
                } else {
                    String idShopper = db.push().getKey();
                    Shopper shopper = new Shopper(emailShopper, passwordShopper);
                    db.child(idShopper).setValue(shopper);

                    Toast.makeText(ShopperRegister.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    progressBarShopper.setVisibility(View.GONE);

                    Intent intent = new Intent(ShopperRegister.this, ShopperLogin.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ShopperRegister.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                progressBarShopper.setVisibility(View.GONE);
            }
        });
    }

    private static class Shopper {
        public String email;
        public String password;

        Shopper(String email, String password) {
            this.email = email;
            this.password = password;
        }
    }
}
