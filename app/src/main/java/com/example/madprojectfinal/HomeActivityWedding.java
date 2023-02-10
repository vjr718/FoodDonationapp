package com.example.madprojectfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.location.LocationListener;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivityWedding extends AppCompatActivity {

    Button b1,b3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_wedding);

        b1=findViewById(R.id.addf);
        //2=findViewById(R.id.modf);
        b3=findViewById(R.id.delf);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivityWedding.this,addfood.class);
                startActivity(intent);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivityWedding.this,DeleteFoodActivtiy.class);
                startActivity(intent);
            }
        });
        //b1.setOnClickListener(new View.OnClickListener() {

    }
}