package com.example.android.sevensisters;

import static android.content.ContentValues.TAG;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.android.sevensisters.data.MyDbHandler;

import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sdp_signup =  getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sdp_signup.edit();
        Intent signup = new Intent(this,SignUp.class);
        if(sdp_signup.contains("signup") == false)
        {
             //openOrCreateDatabase()
             MyDbHandler dbHandler = new MyDbHandler(MainActivity.this);
             editor.putBoolean("signup",true).apply();
             editor.apply();
             Log.i(TAG, "onCreate: sign_up will start-------------------------------------- ");
             startActivity(signup);
        }
        else{
            Log.i(TAG, "onCreate: sign_up will not start-------------------------------------- ");
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