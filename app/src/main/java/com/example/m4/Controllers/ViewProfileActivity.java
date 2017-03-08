package com.example.m4.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.m4.R;
import com.example.m4.models.Models;

import java.util.HashMap;

public class ViewProfileActivity extends AppCompatActivity {
    TextView mFieldProfileEmail;
    TextView mFieldProfileAddress;
    TextView mFieldProfileTitle;
    Button mBtnEditProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        //get buttons and textviews
        mFieldProfileEmail = (TextView) findViewById(R.id.field_profile_email);
        mFieldProfileAddress = (TextView) findViewById(R.id.field_profile_address);
        mFieldProfileTitle = (TextView) findViewById(R.id.field_profile_title);
        mBtnEditProfile = (Button) findViewById((R.id.btn_goto_Edit));

        //set button
        Button btnGotoProfile = (Button) findViewById(R.id.btn_goto_Edit);
        btnGotoProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ViewProfileActivity.this, EditProfileActivity.class);
                startActivity(intent);
            }
        });

        //display user info
        HashMap profileData = Models.accountInSession.getProfileData();
        if (profileData != null) {
            mFieldProfileEmail.setText((String) profileData.get("email"));
            mFieldProfileTitle.setText((String) profileData.get("title"));
            mFieldProfileAddress.setText((String) profileData.get("address"));
        }
    }
}