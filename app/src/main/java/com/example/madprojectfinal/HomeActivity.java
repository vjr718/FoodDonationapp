package com.example.madprojectfinal;

import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class HomeActivity extends AppCompatActivity {
    Button b1;
    TextView t1;
    String addr;
    List<Address> addressList;
    @FloatRange double stla,stlo,endla,endlo;
    FusedLocationProviderClient flp;
    DBHelperWeddingFood db;
    float res[]=new float[10];
    ArrayList<FoodDetail> menuOptions = new ArrayList<>();
    @SuppressLint("Range")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        LinearLayout cl = findViewById(R.id.homemain);
        flp = LocationServices.getFusedLocationProviderClient(this);
        AnimationDrawable adb = (AnimationDrawable) cl.getBackground();
        adb.setEnterFadeDuration(2500);
        adb.setExitFadeDuration(5000);
        adb.start();
        String str;
        db = new DBHelperWeddingFood(this);
        b1 = findViewById(R.id.wlocc);
        t1 = findViewById(R.id.tvlocc1);
        /*b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(HomeActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                } else {
                    ActivityCompat.requestPermissions(HomeActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }

            }


        });*/
        getLocation();

                menuOptions=db.checkuserwall();



        ListView listView = findViewById(R.id.list_view);
        int i;
        for(FoodDetail sub:menuOptions)
        {
            i=0;
            Geocoder gc=new Geocoder(this);
            try {
               //error
                addressList=gc.getFromLocationName(sub.loc,1);
                        if(addressList!=null)
                        {
                            endla=addressList.get(0).getLatitude();
                            endlo=addressList.get(0).getLongitude();
                        }
            } catch (IOException e) {
                e.printStackTrace();
            }
            Double d=distance(stla,stlo,endla,endlo,'K');
            sub.dist=d;

        }
        ArrayAdapter<FoodDetail> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, menuOptions);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FoodDetail sfd= menuOptions.get(position);
                Intent in=new Intent(HomeActivity.this,FoodDetailsSelected.class);

                in.putExtra("selected_item",sfd.toString());
                in.putExtra("address",sfd.loc);
                in.putExtra("id",sfd.sid);
                in.putExtra("lat",endla);
                in.putExtra("lon",endlo);
                startActivity(in);
            }
        });

    }
    private double distance(double lat1, double lon1, double lat2, double lon2, char unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == 'K') {
            dist = dist * 1.609344;
        } else if (unit == 'N') {
            dist = dist * 0.8684;
        }
        return (dist);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts decimal degrees to radians             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts radians to decimal degrees             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
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
                        Geocoder gc=new Geocoder(HomeActivity.this, Locale.getDefault());

                        List<Address> address=gc.getFromLocation(loc.getLatitude(),loc.getLongitude(),1);

                        t1.setText(Html.fromHtml("<font color='#6200EE'><b>Address:</b><br></font>"+address.get(0).getAddressLine(0)));
                        addr=address.get(0).getAddressLine(0);
                        stla=address.get(0).getLatitude();
                        stlo=address.get(0).getLongitude();


                    }

                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}