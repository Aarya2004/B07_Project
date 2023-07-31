package com.example.b07project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StoreOwnerLogin extends AppCompatActivity {

    FirebaseDatabase firedb;
    boolean isFound = false;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_owner_login);
        firedb = FirebaseDatabase.getInstance("https://b07-project-45a16-default-rtdb.firebaseio.com/");
        Button submitButton = findViewById(R.id.submitLogin);
        Button registerRedirect = findViewById(R.id.storeOwnerRegisterRedirect);
        Button shopperRedirect = findViewById(R.id.storeOwnerShopperRedirect);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check_user_exists(view);
            }
        });

        registerRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StoreOwnerRegister.class);
                startActivity(intent);
                finish();
            }
        });

        shopperRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ShopperLogin.class);
                startActivity(intent);
                finish();
            }
        });

    }


    public void check_user_exists(View view){
        DatabaseReference db = this.firedb.getReference();

        EditText userEmailText = (EditText) findViewById(R.id.storeOwnerEmailLogin);
        String email = userEmailText.getText().toString();
        String id = String.valueOf(email.hashCode());
        userEmailText.setText("");
        final String[] dbpassword = new String[1];
        final String[] dbstorename = new String[1];
        db.child("Owners").orderByKey().get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                    Toast myToast = Toast.makeText(view.getContext(), "Database Error!", Toast.LENGTH_SHORT);
                    myToast.show();
                } else {
                    for (DataSnapshot ds : task.getResult().getChildren()) {
                        String value = String.valueOf(ds.getValue());
                        if (value.equals(id)) {
                            isFound = true;
                            dbpassword[0] = ds.child(value).child("password").getValue().toString();
                            dbstorename[0] = ds.child(value).child("Store Name").getValue().toString();
                        }
                    }
                }
            }
        });

        if(!isFound){
            Toast myToast = Toast.makeText(view.getContext(),"No such user exists!", Toast.LENGTH_SHORT);
            myToast.show();
            return;
        }

        EditText userPassText = (EditText) findViewById(R.id.storeOwnerPasswordLogin);
        String password = userPassText.getText().toString();
        userPassText.setText("");

        if(!password.equals(dbpassword[0])){
            isFound = false;
            Toast myToast = Toast.makeText(view.getContext(),"Incorrect password!", Toast.LENGTH_SHORT);
            myToast.show();
            return;
        }else{
            Toast myToast = Toast.makeText(view.getContext(),"Logged in as Store Owner!", Toast.LENGTH_SHORT);
            myToast.show();
            StoreOwner owner = new StoreOwner(id, dbstorename[0], email, password);
            new StoreOwnerMain(owner);
            Intent intent = new Intent(getApplicationContext(), StoreOwnerHomepage.class);
            startActivity(intent);
            finish();
        }
    }
}