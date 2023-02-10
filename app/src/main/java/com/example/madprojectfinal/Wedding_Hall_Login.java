package com.example.madprojectfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Wedding_Hall_Login extends AppCompatActivity {
    EditText user,pass;
    Button signin;
    DBHelperwedding db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wedding_hall_login);
        user=findViewById(R.id.wedid1);
        pass=findViewById(R.id.passwordw1);

        signin=findViewById(R.id.signinw1);
        db=new DBHelperwedding(this);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String wedi=user.getText().toString();

                String passw=pass.getText().toString();
                if(TextUtils.isEmpty(wedi)||TextUtils.isEmpty(passw))
                {
                    Toast.makeText(Wedding_Hall_Login.this,"Please enter all fields correctly",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean checkuserpass=db.checkpassw(wedi,passw);
                    if(checkuserpass)
                    {
                        Toast.makeText(Wedding_Hall_Login.this,"Logged in Successfully",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),HomeActivityWedding.class);
                        startActivity(intent);

                    }
                    else
                    {
                        Toast.makeText(Wedding_Hall_Login.this,"Login Failed,try again!",Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
    }
}