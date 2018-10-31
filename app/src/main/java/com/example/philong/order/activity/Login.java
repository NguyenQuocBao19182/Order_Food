package com.example.philong.order.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.philong.order.R;

public class Login extends AppCompatActivity {
private EditText edt_user,edt_pass;
private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }
    void init(){
        edt_user=findViewById(R.id.edit_text_user);
        edt_pass=findViewById(R.id.edit_text_pass);
        btn_login=findViewById(R.id.button_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   String user= edt_user.getText().toString();
                  String pass= edt_pass.getText().toString();
                if(user.equals("bao")) {
                    startActivity(new Intent(Login.this, MainActivity.class));
                    Toast.makeText(Login.this,"admin", Toast.LENGTH_SHORT).show();
                }


               else
                   if (user.equals("khach")) {
                       Toast.makeText(Login.this, "khach hang", Toast.LENGTH_SHORT).show();
                       startActivity(new Intent(Login.this, FormCustomer.class));
                   }
                else Toast.makeText(Login.this, "khach hang", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
