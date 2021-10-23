package com.example.loginform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.DBhelper;

public class login extends AppCompatActivity {

    EditText loginName,loginPass;
    Button btnLogin;
    DBhelper myDB2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginName=(EditText) findViewById(R.id.eLname);
        loginPass=(EditText) findViewById(R.id.eLpassword);
        btnLogin=(Button) findViewById(R.id.regbtn);

        myDB2= new DBhelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userLN = loginName.getText().toString();
                String passL = loginPass.getText().toString();

                if (userLN.equals("") || passL.equals("")) {
                    Toast.makeText(login.this, "Fill All Login Fields", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean result = myDB2.checkPassword(userLN, passL);
                    if (result == true) {
                        Intent intent= new Intent(getApplicationContext(),homePage.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(login.this, "Invalid data", Toast.LENGTH_SHORT).show();

                    }


                }
            }
        });

}
}