package com.example.android.sevensisters;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
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
        View blog = findViewById(R.id.blog_icon);
        Intent blogIntent = new Intent(this,BlogActivity.class);
        blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(blogIntent);
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

        TextView phone_no = findViewById(R.id.profile_phone_no);
        TextView Name = findViewById(R.id.profile_name);
        TextView city = findViewById(R.id.profile_city);
        TextView email = findViewById(R.id.profile_email_id);

        String name = sdp_signup.getString("name",null);
        String username = sdp_signup.getString("username",null);
        String user_email = sdp_signup.getString("user_email",null);
        String user_phone = sdp_signup.getString("user_phone",null);
        String person_city = sdp_signup.getString("person_city",null);

        phone_no.setText(user_phone);
        Name.setText(name);
        city.setText(person_city);
        email.setText(user_email);
        MyDbHandler dbHandler = new MyDbHandler(Profile.this);
        Log.i(TAG, "onCreate: mydb handler created for the second time------------------------------------------");
        List<Trip> trip_list = dbHandler.getAllTrips();
        for(int i=0; i<trip_list.size(); ++i){
            Log.i(TAG, "onCreate: "+ trip_list.get(i).trip_id + " "+ trip_list.get(i).location_list + " "+ trip_list.get(i).start_date + "------------------------------------");

        }
        Log.i(TAG, "onCreate: number of trips : " + trip_list.size()+ "-----------------------------------------");
        //set trips
        TripAdapter tripArrayAdapter = new TripAdapter(Profile.this,trip_list);
        ListView history_list = findViewById(R.id.history_list);
        history_list.setAdapter(tripArrayAdapter);

        history_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        /*history_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            void deleteItem(MenuItem m){

            }
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                View item = (View) history_list.getSelectedItem();
                PopupMenu popup = new PopupMenu(Profile.this,item);      //(context,View anchor)
                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.trip_delete_menu,popup.getMenu());
                popup.show();
                View menu = findViewById(R.menu.trip_delete_menu);
            }
        });*/
        TextView login_out = findViewById(R.id.login_out_button);
        if(sdp_signup.getBoolean("logged_in",false))
            login_out.setText(R.string.log_out);
        else login_out.setText(R.string.log_in);

        login_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sdp_signup.getBoolean("logged_in",false)) {
                    login_out.setText(R.string.log_in);
                    editor.putBoolean("logged_in",false).apply();
                    phone_no.setText("");
                    Name.setText("");
                    city.setText("");
                    email.setText("");

                    editor.putString("name","").apply();
                    editor.putString("username","").apply();
                    editor.putString("password","").apply();
                    editor.putString("user_email","").apply();
                    editor.putString("user_phone","").apply();
                    editor.putString("person_city","").apply();
                    editor.apply();
                }
                else {
                    login_out.setText(R.string.log_out);
                    Intent sign_in = new Intent(Profile.this,SignIn.class);
                    startActivity(sign_in);
                }
            }
        });
    }
}