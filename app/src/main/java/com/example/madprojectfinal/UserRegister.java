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

public class UserRegister extends AppCompatActivity {
    EditText ngo,pass,phone,email,repass;
    Button signup,signin;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        ngo=findViewById(R.id.ngo);

        pass=findViewById(R.id.password);
        phone=findViewById(R.id.phone);
        email=findViewById(R.id.email);
        signin=findViewById(R.id.signin);
        signup=findViewById(R.id.signup);
        repass=findViewById(R.id.repassword);
        db=new DBHelper(this);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ngoi=ngo.getText().toString();
                String ph=phone.getText().toString();
                String passw=pass.getText().toString();
                String rep=repass.getText().toString();
                if(TextUtils.isEmpty(ngoi)||TextUtils.isEmpty(ph)||TextUtils.isEmpty(passw)||TextUtils.isEmpty(rep))
                {
                    Toast.makeText(UserRegister.this,"Please enter all fields correctly",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(passw.equals(rep))
                    {
                        Boolean check=db.checkuser(ngoi);
                        if(check==false)
                        {
                            Boolean insert=db.insertData(ngoi,passw,ph);
                            if(insert==true)
                            {
                                Toast.makeText(UserRegister.this,"Registered SSuccessfully",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(getApplicationContext(),addfood.class);
                               //Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(UserRegister.this,"Registered Failed!",Toast.LENGTH_SHORT).show();

                            }

                        }
                        else
                        {
                            Toast.makeText(UserRegister.this,"User Already Exists!",Toast.LENGTH_SHORT).show();

                        }

                    }
                    else
                    {
                        Toast.makeText(UserRegister.this,"Password Mismatch!",Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
        //b1.setOnClickListener(new View.OnClickListener() {

    }
}