package com.example.android.sevensisters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class AddTrip extends AppCompatActivity {
    static class Trip{
        int touristC ;
        String dest;
        Date start = new Date();
        Date end = new Date();
        public Trip(int touristC,Date start, Date end ,String dest)
        {
            this.touristC = touristC;
            this.dest = dest;
            this.start = start;
            this.end = end;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        View add_trip_button = findViewById(R.id.add_trip_button);
        //tourist count
        EditText tourist_count = findViewById(R.id.tourist_count);
        ImageButton home = findViewById(R.id.home_icon);
        Intent homeI = new Intent(this,MainActivity.class);
        home.setBackgroundColor(Color.parseColor("#013220"));
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(homeI);
                overridePendingTransition(0, 0);
                finish();
            }
        });
        ImageButton profile = findViewById(R.id.profile_icon);
        profile.setBackgroundColor(Color.parseColor("#013220"));
        Intent profileI = new Intent(this,MainActivity.class);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(profileI);
                overridePendingTransition(0, 0);
                finish();
            }
        });
        ImageButton discover = findViewById(R.id.discover_icon);
        discover.setBackgroundColor(Color.parseColor("#013220"));
        Intent discoverI = new Intent(this,Discover.class);
        discover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(discoverI);
                overridePendingTransition(0, 0);
                finish();
            }
        });
        ImageButton add_trip = findViewById(R.id.add_trip_icon);
        add_trip.setBackgroundColor(Color.parseColor("#013220"));
        //destination
        EditText destination = findViewById(R.id.destination_string);
        add_trip_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int touristC=Integer.parseInt(String.valueOf(tourist_count.getText()));
                String dest = String.valueOf(destination.getText());
            }
        });
    }
}