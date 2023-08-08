package com.example.b07projectself;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivityView extends AppCompatActivity {

    LoginActivityPresenter presenter;
    EditText emailAddress;
    EditText password;

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.login);
        presenter = new LoginActivityPresenter(this, new LoginActivityModel());

        password = findViewById(R.id.editPassword);
        emailAddress = findViewById(R.id.editEmailAddress);

        ((Button) findViewById(R.id.loginButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { presenter.presenterLogin(LoginActivityView.this, emailAddress, password); }
        });
        ((Button) findViewById(R.id.asShopper)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { presenter.presenterRegisterShopper(LoginActivityView.this, emailAddress, password); }
        });
        ((Button) findViewById(R.id.asOwner)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerOwner();
            }
        });

    }

    public void toApp(User user) {
        Intent intent = new Intent(LoginActivityView.this, user.isStoreOwner() ? OwnerActivity.class : ShopperActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("user", user);
        startActivity(intent);

    }

    public void displayToast(String message){
        Toast.makeText(LoginActivityView.this, message, Toast.LENGTH_SHORT).show();
    }

    public void registerOwner(){
        if (presenter.checkInput(emailAddress) || presenter.checkInput(password)) return;

        View view = View.inflate(this, R.layout.get_store_name, null);
        Button close = view.findViewById(R.id.cancelPopup);
        Button reg = view.findViewById(R.id.gotStoreName);
        EditText name = view.findViewById(R.id.editStoreName);

        PopupWindow pop = new PopupWindow(view, ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT, true);
        pop.setOutsideTouchable(false);
        pop.showAtLocation(findViewById(R.id.loginparent), Gravity.CENTER, 0, 0);
        close.setOnClickListener(v -> {pop.dismiss();});

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference db = FirebaseDatabase.getInstance().getReference();
                pop.setFocusable(false);
                presenter.presenterRegisterOwner(LoginActivityView.this, db, emailAddress, password, name, pop);
            }
        });
    }
}
