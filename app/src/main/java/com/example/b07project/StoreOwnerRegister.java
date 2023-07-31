package com.example.b07project;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StoreOwnerRegister extends AppCompatActivity {
    FirebaseDatabase firedb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_owner_register);
        firedb = FirebaseDatabase.getInstance("https://b07-project-45a16-default-rtdb.firebaseio.com/");
        Button submitButton = findViewById(R.id.submitStoreOwnerRegister);
        Button loginRedirect = findViewById(R.id.storeOwnerLoginRedirect);
        Button shopperRedirect = findViewById(R.id.switchToShopperLogin);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enter_into_db();
                Toast myToast = Toast.makeText(StoreOwnerRegister.this,"Successfully Registered!", Toast.LENGTH_SHORT);
                myToast.show();
                Intent intent = new Intent(StoreOwnerRegister.this, StoreOwnerLogin.class);
                startActivity(intent);
                finish();
            }
        });

        loginRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StoreOwnerRegister.this, StoreOwnerLogin.class);
                startActivity(intent);
                finish();
            }
        });

        shopperRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StoreOwnerRegister.this, ShopperLogin.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void enter_into_db(){
        DatabaseReference db = firedb.getReference();
        String storeOwnerEmail;
        String storeOwnerPassword;
        String storeOwnerStoreName;
        String storeOwnerId;
        EditText storeOwnerRegisterEmailInput = findViewById(R.id.storeOwnerEmailRegister);
        EditText storeOwnerRegisterPasswordInput = findViewById(R.id.storeOwnerPasswordRegister);
        EditText storeOwnerRegisterStoreNameInput = findViewById(R.id.storeOwnerStoreNameRegister);

        storeOwnerEmail = String.valueOf(storeOwnerRegisterEmailInput.getText());
        storeOwnerPassword = String.valueOf(storeOwnerRegisterPasswordInput.getText());
        storeOwnerStoreName = String.valueOf(storeOwnerRegisterStoreNameInput.getText());
        storeOwnerId = String.valueOf(storeOwnerEmail.hashCode());

        if (TextUtils.isEmpty(storeOwnerEmail)){
            Toast.makeText(StoreOwnerRegister.this, "Empty Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(storeOwnerPassword)){
            Toast.makeText(StoreOwnerRegister.this, "Empty Password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(storeOwnerStoreName)){
            Toast.makeText(StoreOwnerRegister.this, "Empty Store Name", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean userExists = user_exists(db, storeOwnerId);
        if(userExists == true){
            Toast myToast = Toast.makeText(getApplicationContext(), "User Already Exists!", Toast.LENGTH_SHORT);
            myToast.show();
            return;
        }

        String storeId;
        storeId = String.valueOf(storeOwnerStoreName.hashCode());

        db.child("Owners").child(storeOwnerId).child("email").setValue(storeOwnerEmail);
        db.child("Owners").child(storeOwnerId).child("password").setValue(storeOwnerPassword);
        db.child("Owners").child(storeOwnerId).child("Store Name").setValue(storeOwnerStoreName);
        db.child("Owners").child(storeOwnerId).child("Products");
        db.child("Owners").child(storeOwnerId).child("Orders");
    }

    public boolean user_exists(DatabaseReference db, String id){
        final boolean[] isFound = {false};
        db.child("Owners").orderByKey().get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                    Toast myToast = Toast.makeText(StoreOwnerRegister.this, "Database Error!", Toast.LENGTH_SHORT);
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
