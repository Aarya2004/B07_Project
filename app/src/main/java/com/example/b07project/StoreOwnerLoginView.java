package com.example.b07project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

public class StoreOwnerLoginView extends AppCompatActivity {

    StoreOwnerLoginPresenter presenter;

    EditText userEmailText;

    EditText userPassText;

    String email;
    String password;
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_owner_login);
        Button submitButton = findViewById(R.id.submitLogin);
        Button registerRedirect = findViewById(R.id.storeOwnerRegisterRedirect);
        Button shopperRedirect = findViewById(R.id.storeOwnerShopperRedirect);
        presenter = new StoreOwnerLoginPresenter(this, new StoreOwnerLoginModel());

        userEmailText = (EditText) findViewById(R.id.storeOwnerEmailLogin);
        userPassText = (EditText) findViewById(R.id.storeOwnerPasswordLogin);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = userEmailText.getText().toString();
                id = String.valueOf(email.hashCode());
                password = userPassText.getText().toString();

                userPassText.setText("");
                userEmailText.setText("");

                presenter.check_user_exists(email, password, id);
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

    public void displayToast(String message){
        Toast myToast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        myToast.show();
    }

    public void user_confirmed(String storeName){
        StoreOwner owner = new StoreOwner(id, storeName, email, password);
        new StoreOwnerMain(owner);
        Intent intent = new Intent(StoreOwnerLoginView.this, StoreOwnerHomepage.class);
        startActivity(intent);
        finish();
    }
}
