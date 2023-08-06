package com.example.b07project;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

    public void enter_into_db() {
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

        if (TextUtils.isEmpty(storeOwnerEmail)) {
            Toast.makeText(StoreOwnerRegister.this, "Empty Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(storeOwnerPassword)) {
            Toast.makeText(StoreOwnerRegister.this, "Empty Password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(storeOwnerStoreName)) {
            Toast.makeText(StoreOwnerRegister.this, "Empty Store Name", Toast.LENGTH_SHORT).show();
            return;
        }

        db.child("Owners").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (emailExists(snapshot, storeOwnerEmail) || storeNameExists(snapshot, storeOwnerStoreName)) {
                    Toast myToast = Toast.makeText(getApplicationContext(), "Email or Store Name Already Exists!", Toast.LENGTH_SHORT);
                    myToast.show();
                } else {
                    db.child("Owners").child(storeOwnerId).child("email").setValue(storeOwnerEmail);
                    db.child("Owners").child(storeOwnerId).child("password").setValue(storeOwnerPassword);
                    db.child("Owners").child(storeOwnerId).child("Store Name").setValue(storeOwnerStoreName);
                    db.child("Owners").child(storeOwnerId).child("Products");
                    db.child("Owners").child(storeOwnerId).child("Orders");
                    Toast myToast = Toast.makeText(StoreOwnerRegister.this, "Successfully Registered!", Toast.LENGTH_SHORT);
                    myToast.show();
                    Intent intent = new Intent(StoreOwnerRegister.this, StoreOwnerLogin.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public boolean emailExists(DataSnapshot snapshot, String email) {
        for (DataSnapshot child : snapshot.getChildren()) {
            String currEmail = String.valueOf(child.child("email").getValue());
            if (currEmail.equals(email)) {
                return true;
            }
        }
        return false;
    }

    public boolean storeNameExists(DataSnapshot snapshot, String storeName) {
        for (DataSnapshot child : snapshot.getChildren()) {
            String currStoreName = String.valueOf(child.child("Store Name").getValue());
            if (currStoreName.equals(storeName)) {
                return true;
            }
        }
        return false;
    }
}
