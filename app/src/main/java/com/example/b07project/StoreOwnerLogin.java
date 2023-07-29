package com.example.b07project;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

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

//    public void onClickAdd(View view) {
//        DatabaseReference ref = db.getReference();
//    }


        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_owner_login);
        firedb = FirebaseDatabase.getInstance("https://b07-project-45a16-default-rtdb.firebaseio.com/");
    }

    public void check_user_exists(){
        DatabaseReference db = this.firedb.getReference();

        db.child("Owners").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("demo", "Error getting data", task.getException());
                }
                else {
                    Log.i("demo", task.getResult().getValue().toString());
                }
            }
        });

        EditText userEmailText = (EditText) findViewById(R.id.storeOwnerEmailLogin);
        String email = userEmailText.getText().toString();
        userEmailText.setText("");
        DatabaseReference query = db.child("Owners").child(email);
        final String[] dbpassword = new String[1];
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                isFound = snapshot.exists();
                dbpassword[0] = snapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        if(isFound == false){
            Toast myToast = Toast.makeText(getApplicationContext(),"No such user exists!", Toast.LENGTH_SHORT);
            myToast.show();
            return;
        }

        EditText userPassText = (EditText) findViewById(R.id.storeOwnerPasswordLogin);
        String password = userPassText.getText().toString();
        userPassText.setText("");

        if(password != dbpassword[0]){
            Toast myToast = Toast.makeText(getApplicationContext(),"Incorrect password!", Toast.LENGTH_SHORT);
            myToast.show();
            return;
        }
    }

}