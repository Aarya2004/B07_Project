package com.example.b07project;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.annotations.Nullable;

public class ShoppersProductPreview extends AppCompatActivity {

    private ImageView productImage;
    private TextView productNameView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_preview_layout);

        productImage = findViewById(R.id.productImage);
        productNameView = findViewById(R.id.productNameView);


        String productName = "Name";
        int productImageId = R.drawable.productImage;

        productNameView.setText(productName);
        productImage.setImageResource(productImageId);
    }
}
