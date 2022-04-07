package com.example.android.sevensisters;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.sevensisters.data.MyDbHandler;
import com.example.android.sevensisters.data.Trip;

import java.util.ArrayList;
import java.util.List;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        View home = findViewById(R.id.home_icon);
        Intent homeIntent = new Intent(this,MainActivity.class);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(homeIntent);
                overridePendingTransition(0, 0);
                finish();
            }
        });

        View discover = findViewById(R.id.discover_icon);
        Intent discoverIntent = new Intent(this,Discover.class);
        discover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(discoverIntent);
                overridePendingTransition(0, 0);
                finish();
            }
        });

        View addTrip = findViewById(R.id.add_trip_icon);
        Intent addTripIntent = new Intent(this,AddTrip.class);
        addTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(addTripIntent);
                overridePendingTransition(0, 0);
                finish();
            }
        });
        SharedPreferences sdp_signup =  getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sdp_signup.edit();
        String name = sdp_signup.getString("name",null);
        String username = sdp_signup.getString("username",null);
        String user_email = sdp_signup.getString("user_email",null);
        String user_phone = sdp_signup.getString("user_phone",null);
        String person_city = sdp_signup.getString("person_city",null);
        TextView phone_no = findViewById(R.id.profile_phone_no);
        phone_no.setText(user_phone);
        TextView Name = findViewById(R.id.profile_name);
        Name.setText(name);
        TextView city = findViewById(R.id.profile_city);
        city.setText(person_city);
        TextView email = findViewById(R.id.profile_email_id);
        email.setText(user_email);
        MyDbHandler dbHandler = new MyDbHandler(Profile.this);
        Log.i(TAG, "onCreate: mydb handler created for the second time------------------------------------------");
        List<Trip> trip_list = dbHandler.getAllTrips();
        //for(int i=0; i<trip_list.size(); ++i){
          //  Log.i(TAG, "onCreate: "+ trip_list.get(i).state_name + " "+ trip_list.get(i).location_list + " "+ trip_list.get(i).start_date);
        //}
        Log.i(TAG, "onCreate: number of trips : " + trip_list.size()+ "-----------------------------------------");
        TripAdapter tripArrayAdapter = new TripAdapter(Profile.this,trip_list);
        ListView history_list = findViewById(R.id.history_list);
        history_list.setAdapter(tripArrayAdapter);
    }
}