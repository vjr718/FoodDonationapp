package com.example.madprojectfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText user,pass,phone;
    Button signin;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user=findViewById(R.id.ngo1);
        pass=findViewById(R.id.password1);
        phone=findViewById(R.id.phone1);
        signin=findViewById(R.id.signin1);
        db=new DBHelper(this);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ngoi=user.getText().toString();
                String ph=phone.getText().toString();
                String passw=pass.getText().toString();
                if(TextUtils.isEmpty(ngoi)||TextUtils.isEmpty(ph)||TextUtils.isEmpty(passw))
                {
                    Toast.makeText(LoginActivity.this,"Please enter all fields correctly",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean checkuserpass=db.checkpass(ngoi,ph,passw);
                    if(checkuserpass)
                    {
                        Toast.makeText(LoginActivity.this,"Logged in Successfully",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(intent);

                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this,"Login Failed,try again!",Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
    }
}