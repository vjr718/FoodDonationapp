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

public class WeddingHallActivity extends AppCompatActivity {
    EditText user,pass,phone,name,repass;
    Button signup,signin;
    DBHelperwedding db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wedding_hall);
        user=findViewById(R.id.wedid);
        name=findViewById(R.id.weddingname);
        pass=findViewById(R.id.passwordw);
        phone=findViewById(R.id.phonew);
        signin=findViewById(R.id.signinw);
        signup=findViewById(R.id.signupw);
        repass=findViewById(R.id.repasswordw);
        db=new DBHelperwedding(this);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String wedi=user.getText().toString();
                String wedn=name.getText().toString();
                String passw=pass.getText().toString();
                String rep=repass.getText().toString();
                String ph=phone.getText().toString();
                if(TextUtils.isEmpty(wedi)||TextUtils.isEmpty(wedn)||TextUtils.isEmpty(passw)||TextUtils.isEmpty(rep)||TextUtils.isEmpty(ph))
                {
                    Toast.makeText(WeddingHallActivity.this,"Please enter all fields correctly",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(passw.equals(rep))
                    {
                        Boolean check=db.checkuserw(wedi);
                        if(check==false)
                        {
                            Boolean insert=db.insertDataw(wedi,passw,wedn,ph);
                            if(insert==true)
                            {
                                Toast.makeText(WeddingHallActivity.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(getApplicationContext(),HomeActivityWedding.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(WeddingHallActivity.this,"Registered Failed!",Toast.LENGTH_SHORT).show();

                            }

                        }
                        else
                        {
                            Toast.makeText(WeddingHallActivity.this,"User Already Exists!",Toast.LENGTH_SHORT).show();

                        }

                    }
                    else
                    {
                        Toast.makeText(WeddingHallActivity.this,"Password Mismatch!",Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Wedding_Hall_Login.class);
                startActivity(intent);
            }
        });
        //b1.setOnClickListener(new View.OnClickListener() {

    }
}