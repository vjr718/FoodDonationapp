package com.example.madprojectfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class addfood extends AppCompatActivity {
    Button b1,b2;
    TextView t1;
    EditText wid,fooditems,foodqty,pickdate,picktime;
    CheckBox c1,c2,c3;
    FusedLocationProviderClient flp;
    String addr,ftype;
    DBHelperWeddingFood db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfood);
        b1 = findViewById(R.id.wloc);
        t1 = findViewById(R.id.tvloc);
        wid=findViewById(R.id.wedid1);
        c1=findViewById(R.id.checkBoxv);
        c2=findViewById(R.id.checkBoxn);
        c3=findViewById(R.id.checkBoxj);
        fooditems=findViewById(R.id.foodlist);
        foodqty=findViewById(R.id.foodqua);
        pickdate=findViewById(R.id.datep);
        picktime=findViewById(R.id.timep);
        b2=findViewById(R.id.submitw);
        db=new DBHelperWeddingFood(this);

        flp = LocationServices.getFusedLocationProviderClient(this);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(addfood.this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                } else {
                    ActivityCompat.requestPermissions(addfood.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String w_id=wid.getText().toString();
                Boolean veg=c1.isChecked();
                Boolean nveg=c2.isChecked();
                Boolean jain=c3.isChecked();
                String food=fooditems.getText().toString();
                int fqty=Integer.parseInt(foodqty.getText().toString());
                String dt=pickdate.getText().toString();
                String time=picktime.getText().toString();
                if(TextUtils.isEmpty(w_id)||TextUtils.isEmpty(food)||TextUtils.isEmpty(dt)||TextUtils.isEmpty(time))
                {
                    Toast.makeText(addfood.this,"Please enter all fields correctly", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(veg==true && nveg==false )
                    {
                        ftype="Veg";
                    }
                    else if(veg==false && nveg==false && jain==true)
                    {
                        ftype="Jain";
                    }
                    else
                    {
                        ftype="Non-Veg";
                    }
                    Boolean insert=db.insertDataw(w_id,ftype,food,fqty,dt,time,addr);
                    if(insert==true)
                    {
                        Toast.makeText(addfood.this,"Added food Successfully",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),HomeActivityWedding.class);
                        //Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(addfood.this,"Something went wrong...!",Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        flp.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location loc= task.getResult();
                if(loc!=null)
                {
                    //Geocoder gc=new Geocoder(addfood.this, Locale.getDefault());
                    try {
                        Geocoder gc=new Geocoder(addfood.this, Locale.getDefault());

                        List<Address> address=gc.getFromLocation(loc.getLatitude(),loc.getLongitude(),1);

                       t1.setText(Html.fromHtml("<font color='#6200EE'><b>Address:</b><br></font>"+address.get(0).getAddressLine(0)));
                       addr=address.get(0).getAddressLine(0);



                    }

                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}