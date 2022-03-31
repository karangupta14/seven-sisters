package com.example.android.sevensisters;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sdp_signup =  getSharedPreferences("MyPREFERENCES", Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = sdp_signup.edit();
        Intent signup = new Intent(this,SignUp.class);
        if(!sdp_signup.contains("signup_key"))
        {
             editor.putBoolean("signup_key",true);
             editor.apply();
             startActivity(signup);
        }
        //SharedPreferences sharedPreferences = this.getSharedPreferences();
        setContentView(R.layout.activity_main);
        //View home = findViewById(R.id.home_icon);
        //Intent homeIntent = new Intent(this,MainActivity.class);
        //home.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        startActivity(homeIntent);
        //    }
        //});
        View profile = findViewById(R.id.profile_icon);
        Intent profileIntent = new Intent(this,Profile.class);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(profileIntent);
                overridePendingTransition(0, 0);
            }
        });

        View discover = findViewById(R.id.discover_icon);
        Intent discoverIntent = new Intent(this,Discover.class);
        discover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(discoverIntent);
                overridePendingTransition(0, 0);
            }
        });

        View addTrip = findViewById(R.id.add_trip_icon);
        Intent addTripIntent = new Intent(this,AddTrip.class);
        addTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(addTripIntent);
                overridePendingTransition(0, 0);
            }
        });

    }
}