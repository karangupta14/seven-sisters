package com.example.android.sevensisters;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.sql.Array;
import java.util.ArrayList;

public class StateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);
        Bundle extras = getIntent().getExtras();
        String state;
        if(savedInstanceState == null) {
            state = extras.getString("state");
        }
        else state =(String)savedInstanceState.getSerializable("state") ;
        TextView state_name = findViewById(R.id.state_name);
        Log.i(TAG, "onCreate: hello world----------------------------------------------------");
        ListView listView = findViewById(R.id.state_info);
        TextView map_button = findViewById(R.id.map_button);
        Drawable drawable = ContextCompat.getDrawable(this,R.drawable.action_bar_back);
        getSupportActionBar().setBackgroundDrawable(drawable);
        /*------------------- Adding profile icon on Action Bar------------*/
        ImageButton profileImageButton = new ImageButton(this);
        profileImageButton.setMaxWidth(55);
        profileImageButton.setMinimumWidth(53);
        profileImageButton.setMaxHeight(30);
        profileImageButton.setMinimumHeight(30);
        profileImageButton.setBackgroundColor(Color.parseColor("#3B3C36"));
        profileImageButton.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.profile_icon));
        Intent profileInt;
        ActionBar bar = getSupportActionBar();
        bar.setDisplayShowCustomEnabled(true);
        Intent intent = new Intent(this,Profile.class);
        profileImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT);
        ActionBar.LayoutParams params1 = new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.RIGHT);
        bar.setCustomView(profileImageButton,params1);
        /*-----------------------Added profile icon on Action Bar-------------------------------------*/
        map_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StateActivity.this,MapsActivity.class);
                intent.putExtra("state",extras.getString("state"));
                startActivity(intent);
            }
        });
        if(state.equals("assam")) {
            findViewById(R.id.state_image).setBackgroundResource(R.drawable.assam_state);
            state_name.setText("Assam");
        }
        if(state.equals("meghalaya")){
            findViewById(R.id.state_image).setBackgroundResource(R.drawable.meghalaya_state);
            state_name.setText("Meghalaya");
        }
        if(state.equals("tripura")) {
            findViewById(R.id.state_image).setBackgroundResource(R.drawable.tripura_state);
            state_name.setText("Tripura");
        }
        if(state.equals("manipur")){
            findViewById(R.id.state_image).setBackgroundResource(R.drawable.manipur_state);
            state_name.setText("manipur");
        }
        if(state.equals("mizoram")) {
            findViewById(R.id.state_image).setBackgroundResource(R.drawable.mizoram_state);
            state_name.setText("Mizoram");
        }
        if(state.equals("nagaland")){
            findViewById(R.id.state_image).setBackgroundResource(R.drawable.nagaland_state);
            state_name.setText("Nagaland");
        }
        if(state.equals("arunachal")){
            findViewById(R.id.state_image).setBackgroundResource(R.drawable.arunachal_state);
            state_name.setText("Arunachal");
        }
        // populating locations tab as soon as activity is opened
        ArrayList <location> loc = new ArrayList<>();
        LocationAdapter locations = new LocationAdapter(StateActivity.this,loc,"assam");
        for(int i=0; i<8; ++i){
            loc.add(new location(i));
        }
        if(state.equals("assam")) {
            locations = new LocationAdapter(StateActivity.this,loc,"assam");
        }
        if(state.equals("meghalaya")){
            locations = new LocationAdapter(StateActivity.this,loc,"meghalaya");
        }
        if(state.equals("tripura")) {
            locations = new LocationAdapter(StateActivity.this,loc,"tripura");
        }
        if(state.equals("manipur")){
            locations = new LocationAdapter(StateActivity.this,loc,"manipur");
        }
        if(state.equals("mizoram")) {
            locations = new LocationAdapter(StateActivity.this,loc,"mizoram");
        }
        if(state.equals("nagaland")){
            locations = new LocationAdapter(StateActivity.this,loc,"nagaland");
        }
        if(state.equals("arunachal")){
            locations = new LocationAdapter(StateActivity.this,loc,"arunachal");
        }
        listView.setAdapter(locations);
        Log.i(TAG, "onCreate: before selection -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        /*TextView hotel = findViewById(R.id.hotel_dining_tab);

        hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList <hotel> hot = new ArrayList<>();
                HotelAdapter hotels = new HotelAdapter(this,hot,"assam");
                for(int i=0; i<8; ++i){
                    hot.add(new hotel(i));
                }
                if(state.equals("assam")) {
                    hotels = new HotelAdapter(this,hot,"assam");
                }
                if(state.equals("meghalaya")){
                    hotels = new HotelAdapter(this,hot,"meghalaya");
                }
                if(state.equals("tripura")) {
                    hotels = new HotelAdapter(this,hot,"tripura");
                }
                if(state.equals("manipur")){
                    hotels = new HotelAdapter(this,hot,"manipur");
                }
                if(state.equals("mizoram")) {
                    hotels = new HotelAdapter(this,hot,"mizoram");
                }
                if(state.equals("nagaland")){
                    hotels = new HotelAdapter(this,hot,"nagaland");
                }
                if(state.equals("arunachal")){
                    hotels = new HotelAdapter(this,hot,"arunachal");
                }
                listView.setAdapter(hotels);
            }
        });*/
        /*TextView tourism = findViewById(R.id.tourism_tab);
        tourism.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList <location> loc = new ArrayList<>();
                LocationAdapter locations = new LocationAdapter(StateActivity.this,loc,"assam");
                for(int i=0; i<8; ++i){
                    loc.add(new location(i));
                }
                if(state.equals("assam")) {
                    locations = new LocationAdapter(StateActivity.this,loc,"assam");
                }
                if(state.equals("meghalaya")){
                    locations = new LocationAdapter(StateActivity.this,loc,"meghalaya");
                }
                if(state.equals("tripura")) {
                    locations = new LocationAdapter(StateActivity.this,loc,"tripura");
                }
                if(state.equals("manipur")){
                    locations = new LocationAdapter(StateActivity.this,loc,"manipur");
                }
                if(state.equals("mizoram")) {
                    locations = new LocationAdapter(StateActivity.this,loc,"mizoram");
                }
                if(state.equals("nagaland")){
                    locations = new LocationAdapter(StateActivity.this,loc,"nagaland");
                }
                if(state.equals("arunachal")){
                    locations = new LocationAdapter(StateActivity.this,loc,"arunachal");
                }
                listView.setAdapter(locations);
            }
        });*/

                    /*case 2:         //culture and history
                        if(state.equals("assam")) {

                        }
                        if(state.equals("meghalaya")){

                        }
                        if(state.equals("tripura")) {

                        }
                        if(state.equals("manipur")){

                        }
                        if(state.equals("mizoram")) {

                        }
                        if(state.equals("nagaland")){

                        }
                        if(state.equals("arunachal")){

                        }

                    case 3:         //map
                        if(state.equals("assam")) {

                        }
                        if(state.equals("meghalaya")){

                        }
                        if(state.equals("tripura")) {

                        }
                        if(state.equals("manipur")){

                        }
                        if(state.equals("mizoram")) {

                        }
                        if(state.equals("nagaland")){

                        }
                        if(state.equals("arunachal")){

                        }*/
                    //default: break;


    }
}