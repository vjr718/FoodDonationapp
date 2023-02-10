package com.example.madprojectfinal;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class DeleteFoodActivtiy extends AppCompatActivity {
    EditText id;
    Button b1,b2;
    TextView t1;
    DBHelperWeddingFood db;
    String fid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_food_activtiy);
        id=findViewById(R.id.wedid1);
        b1=findViewById(R.id.fetch);
        t1=findViewById(R.id.dbfdet);
        b2=findViewById(R.id.delfood);
        db=new DBHelperWeddingFood(this);
        b1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                fid=id.getText().toString();
                if(TextUtils.isEmpty(fid))
                {
                    Toast.makeText(DeleteFoodActivtiy.this,"Please enter all fields correctly", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    ArrayList<FoodDetail> arr=null;
                    arr=db.checkuserw(fid);
                    if(arr==null)
                    {
                        t1.setText("No records found!");
                    }
                    String listString = arr.stream().map(Object::toString).collect(Collectors.joining(", "));
                    t1.setText(listString);


                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fid=id.getText().toString();
                if(TextUtils.isEmpty(fid))
                {
                    Toast.makeText(DeleteFoodActivtiy.this,"Please enter all fields correctly", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean res=db.deletew(fid);
                    if(res==false)
                    {
                        Toast.makeText(DeleteFoodActivtiy.this,"Something went wrong..", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(DeleteFoodActivtiy.this,"Deleted successfully", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}