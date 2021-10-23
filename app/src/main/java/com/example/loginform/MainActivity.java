package com.example.loginform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.DBhelper;

public class MainActivity extends AppCompatActivity {

    EditText username,password,repassword;
    Button register,existing;
    DBhelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username=(EditText) findViewById(R.id.eTname);
        password =(EditText) findViewById(R.id.etpassword);
        repassword =(EditText) findViewById(R.id.etCpassword);
        register=(Button) findViewById(R.id.regbtn);
        existing=(Button) findViewById(R.id.reregbtn);

        myDB= new DBhelper(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              String userN= username.getText().toString();
              String pass= password.getText().toString();
              String repass= repassword.getText().toString();

              if(userN.equals("") || pass.equals("")||repass.equals("")){
                  Toast.makeText(MainActivity.this, "Fill All Fields", Toast.LENGTH_SHORT).show();
              }else {

                  if(pass.equals(repass)){

                      //check if user exist or no

                      Boolean userCheckResult= myDB.checkUser(userN);
                      //user not exist  1

                       if(userCheckResult==false) {

                           // you are not exist, so insert your data
                           // insertData method is boolean, so it has two return values(if reg successful and reg failed)

                       Boolean regResult= myDB.insertData(userN,pass);

                       if(regResult==true){
                           Toast.makeText(MainActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                           Intent intent= new Intent(getApplicationContext(),login.class);
                           startActivity(intent);

                       }else{

                           Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                       }

                       //user exist  2
                    }else{

                        Toast.makeText(MainActivity.this, "User Already Exists\n Please Login", Toast.LENGTH_SHORT).show();
                    }
                          //if pass != repass
                  }else{
                      Toast.makeText(MainActivity.this, "Password Not Matching", Toast.LENGTH_SHORT).show();


                  }

              }
              

            }
        });
        existing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),login.class);
                startActivity(intent);

            }
        });


    }
}