package com.example.madprojectfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Confirm extends AppCompatActivity {
    TextView t;
    String stra,strd;
    Button b1;
    String temp;
    Double lat,lon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        t=findViewById(R.id.fdett);
        stra=getIntent().getStringExtra("addr");
        strd=getIntent().getStringExtra("details");
        lat=getIntent().getDoubleExtra("lat",0.0);
        lon=getIntent().getDoubleExtra("lon",0.0);
        String tem="geo:";
        temp=tem.concat(lat.toString());
        String temp1=temp.concat(",");
        String temp2=temp1.concat(lon.toString());

        b1=findViewById(R.id.fsubb);
        t.setText(strd);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Map point based on address
                Uri location = Uri.parse(stra);
                // Or map point based on latitude/longitude
                // Uri location = Uri.parse("geo:37.422219,-122.08364?z=14"); // z param is zoom level
                Intent in=new Intent(Intent.ACTION_VIEW);

                in.setData(Uri.parse(temp2));
                Log.d("loc",temp2);
                Intent chooser=Intent.createChooser(in,"Launch Maps");
                startActivity(chooser);
            }
        });
    }
}