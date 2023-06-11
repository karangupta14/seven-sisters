package com.example.android.sevensisters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Discover extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);
        ImageButton assam = findViewById(R.id.assam_image);
        ImageButton meghalaya = findViewById(R.id.meghalaya_image);
        ImageButton mizoram = findViewById(R.id.mizoram_image);
        ImageButton tripura = findViewById(R.id.tripura_image);
        ImageButton manipur = findViewById(R.id.manipur_image);
        ImageButton nagaland = findViewById(R.id.nagaland_image);
        ImageButton arunachal = findViewById(R.id.arunachal_image);
        Drawable drawable = ContextCompat.getDrawable(this,R.drawable.action_bar_back);
        getSupportActionBar().setBackgroundDrawable(drawable);
        Intent state = new Intent(this, StateActivity.class);
        assam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stat="assam";
                state.putExtra("state",stat);
                startActivity(state);
            }
        });
        meghalaya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                state.putExtra("state", "meghalaya");
                startActivity(state);
            }
        });
        tripura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                state.putExtra("state", "tripura");
                startActivity(state);
            }
        });
        nagaland.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                state.putExtra("state", "nagaland");
                startActivity(state);
            }
        });
        mizoram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                state.putExtra("state", "mizoram");
                startActivity(state);
            }
        });
        manipur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                state.putExtra("state", "manipur");
                startActivity(state);
            }
        });
        arunachal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                state.putExtra("state", "arunachal");
                startActivity(state);
            }
        });
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
        View blog = findViewById(R.id.blog_icon);
        Intent blogIntent = new Intent(this,BlogActivity.class);
        blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(blogIntent);
                overridePendingTransition(0, 0);
            }
        });
        /*View profile = findViewById(R.id.profile_icon);
        Intent profileIntent = new Intent(this,Profile.class);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(profileIntent);
                overridePendingTransition(0, 0);
                finish();
            }
        });*/

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
    }
}