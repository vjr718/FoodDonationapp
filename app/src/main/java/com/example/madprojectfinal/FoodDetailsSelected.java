package com.example.madprojectfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FoodDetailsSelected extends AppCompatActivity {
    TextView textView;
    Button b1;
    String sidf,addr;
    DBHelperWeddingFood db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details_selected);
        textView = findViewById(R.id.fdes);
        b1=findViewById(R.id.fsubm);
        String selectedItem = getIntent().getStringExtra("selected_item");
        textView.setText(selectedItem);
        sidf= getIntent().getStringExtra("id");
        addr=getIntent().getStringExtra("address");
        Double lat=getIntent().getDoubleExtra("lat",0.0);
        Double lon=getIntent().getDoubleExtra("lon",0.0);
        db=new DBHelperWeddingFood(this);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    Boolean res=db.deletew(sidf);
                    if(res==false)
                    {
                        Toast.makeText(FoodDetailsSelected.this,"Something went wrong..", Toast.LENGTH_SHORT).show();


                    }
                    else
                    {
                        Toast.makeText(FoodDetailsSelected.this,"Deleted successfully", Toast.LENGTH_SHORT).show();
                        Intent in=new Intent(FoodDetailsSelected.this,Confirm.class);
                        in.putExtra("details",selectedItem);
                        in.putExtra("addr",addr);
                        in.putExtra("lat",lat);
                        in.putExtra("lon",lon);
                        startActivity(in);
                    }

            }
        });

    }
}