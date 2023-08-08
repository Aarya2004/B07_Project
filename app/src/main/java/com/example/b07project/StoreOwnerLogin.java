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
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
        EditText userPassText = (EditText) findViewById(R.id.storeOwnerPasswordLogin);
        String password = userPassText.getText().toString();
        userPassText.setText("");
        userEmailText.setText("");
        DatabaseReference query = db.child("Owners").child(id);

        query.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                String dbpassword = null;
                String dbstorename = null;
                boolean switchactivity = false;
                isFound = snapshot.exists();
                if(isFound){
                    for(DataSnapshot child: snapshot.getChildren()){
                        if(String.valueOf(child.getKey()).equals("password")){
                            dbpassword = String.valueOf(child.getValue());
                        }
                        if(String.valueOf(child.getKey()).equals("Store Name")){
                            dbstorename = String.valueOf(child.getValue());
                        }
                    }
                }

                if(!isFound){
                    Toast myToast = Toast.makeText(view.getContext(),"No such user exists!", Toast.LENGTH_SHORT);
                    myToast.show();
                    return;
                }

                if(!(password.equals(dbpassword))){
                    isFound = false;
                    Toast myToast = Toast.makeText(view.getContext(),"Incorrect password!", Toast.LENGTH_SHORT);
                    myToast.show();
                    return;
                }else{
                    StoreOwner owner = new StoreOwner(id, dbstorename, email, password);
                    new StoreOwnerMain(owner);
                    Intent intent = new Intent(StoreOwnerLogin.this, StoreOwnerHomepage.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

    }
}