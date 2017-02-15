package com.example.m4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Button btnGotoLogin = (Button)findViewById(R.id.btn_goto_login);
        Button btnGotoRegistration = (Button)findViewById(R.id.btn_goto_registration);
        btnGotoLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToLogin();
            }
        });
        btnGotoRegistration.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToRegister();
            }
        });
    }

    public void goToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void goToRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
