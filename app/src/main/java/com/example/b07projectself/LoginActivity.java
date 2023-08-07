package com.example.b07projectself;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    EditText emailAddress;
    EditText password;

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.login);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) mAuth.signOut();

        ((Button) findViewById(R.id.loginButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doLogin();
            }
        });
        ((Button) findViewById(R.id.asShopper)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerShopper();
            }
        });
        ((Button) findViewById(R.id.asOwner)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerOwner();
            }
        });
        password = findViewById(R.id.editPassword);
        emailAddress = findViewById(R.id.editEmailAddress);
    }

    private void doLogin() {
        if (emailAddress.length() == 0 || password.length() == 0) return;
        mAuth.signInWithEmailAndPassword(emailAddress.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseDatabase.getInstance().getReference().child("users").child(mAuth.getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DataSnapshot> task) {
                                    if (!task.isSuccessful()) {
                                        mAuth.getCurrentUser().delete();
                                        return;
                                    }
                                    User u = task.getResult().getValue(User.class);
                                    toApp(u);
                                }
                            });
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void toApp(User user) {

        Intent intent = new Intent(LoginActivity.this, user.isStoreOwner() ? OwnerActivity.class : LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("user", user);
        startActivity(intent);

    }

    private void registerShopper() {
        if (emailAddress.length() == 0 || password.length() == 0) return;
        mAuth.createUserWithEmailAndPassword(emailAddress.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser u = mAuth.getCurrentUser();
                            User user = new User();
                            user.setStoreOwner(false);
                            FirebaseDatabase.getInstance().getReference().child("users").child(u.getUid()).setValue(user);
                            toApp(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void registerOwner() {
        if (emailAddress.length() == 0 || password.length() == 0) return;

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
                if (name.length() == 0) return;
                db.child("stores").child(name.getText().toString()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (task.getResult().exists()) {
                            Toast.makeText(LoginActivity.this, "Store already exists", Toast.LENGTH_LONG).show();
                            pop.setFocusable(true); return;
                        }
                        mAuth.createUserWithEmailAndPassword(emailAddress.getText().toString(), password.getText().toString())
                                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            FirebaseUser u = mAuth.getCurrentUser();
                                            User user = new User();
                                            user.setStoreOwner(true);
                                            user.setStoreName(name.getText().toString());
                                            db.child("users").child(u.getUid()).setValue(user);
                                            Store store = new Store(); store.setOwner(u.getUid());
                                            db.child("stores").child(name.getText().toString()).setValue(store);
                                            toApp(user);
                                        } else {
                                            pop.setFocusable(true);
                                            pop.dismiss();
                                            Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                    }
                });
            }
        });

    }


}
