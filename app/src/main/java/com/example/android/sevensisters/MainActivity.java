package com.example.android.sevensisters;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.*;//AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.sevensisters.data.MyDbHandler;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private Geocoder geocoder;
    GoogleMap googleMap ;
    //final List<Address>[] locations = (List<Address>[]) new Object[1];
    List<Address> locations =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sdp_signup =  getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sdp_signup.edit();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        geocoder = new Geocoder(this);


        Drawable drawable = ContextCompat.getDrawable(this,R.drawable.action_bar_back);

        getSupportActionBar().setBackgroundDrawable(drawable);
        //getSupportActionBar().setDisplayShowCustomEnabled(true);
        FrameLayout layout = findViewById(R.id.frameLayout);
        //ListView locations_list = layout.findViewById(R.id.locations_list);
        //locations_list.setRotation(90f);
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
        /*ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                InputStream file1 = getContext().getAssets().open("locations/" + state + "/" + position + "/Image" + im_no + ".jpg");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
        /*LinearLayout layout = findViewById(R.id.image_list);
        InputStream file1=null;
        try {
            file1 = getAssets().open("locations/" + "assam" + "/" + 1 + "/Image" + 1 + ".jpg",MODE_PRIVATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Drawable d1 = Drawable.createFromStream(file1, null);
        ImageButton image1 = layout.findViewById(R.id.pick_a);
        image1.setImageDrawable(d1);

        InputStream file2=null;
        try {
            file2 = getAssets().open("locations/" + "meghalaya" + "/" + 2 + "/Image" + 1 + ".jpg",MODE_PRIVATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Drawable d2 = Drawable.createFromStream(file2, null);
        ImageButton image2 = layout.findViewById(R.id.pick_b);
        image2.setImageDrawable(d2);

        InputStream file3=null;
        try {
            file3 = getAssets().open("locations/" + "mizoram" + "/" + 3 + "/Image" + 2 + ".jpg",MODE_PRIVATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Drawable d3 = Drawable.createFromStream(file3, null);
        ImageButton image3 = layout.findViewById(R.id.pick_c);
        image3.setImageDrawable(d3);*/

        Intent signup = new Intent(this,SignUp.class);
        if(sdp_signup.contains("signup") == false)
        {

             MyDbHandler dbHandler = new MyDbHandler(MainActivity.this);
             editor.putBoolean("signup",true).apply();
             editor.putBoolean("logged_in",false).apply();
             editor.apply();
             Log.i(TAG, "onCreate: sign_up will start-------------------------------------- ");
             startActivity(signup);

        }
        else{
            Log.i(TAG, "onCreate: sign_up will not start-------------------------------------- ");
        }

        setContentView(R.layout.activity_main);

        // click listeners for activities

        View profile = findViewById(R.id.profile_icon);
        Intent profileIntent = new Intent(this,Profile.class);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(profileIntent);
                overridePendingTransition(0, 0);
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
        ArrayList<suggestedLocation> list = new ArrayList<>();
        list.add(new suggestedLocation("arunachal",""));
        list.add(new suggestedLocation("assam",""));
        list.add(new suggestedLocation("manipur",""));
        list.add(new suggestedLocation("meghalaya",""));
        list.add(new suggestedLocation("mizoram",""));
        list.add(new suggestedLocation("nagaland",""));
        list.add(new suggestedLocation("tripura",""));
        suggestedAdapter adapter = new suggestedAdapter(this,list);
        ListView suggested_list = findViewById(R.id.locations_list);
        suggested_list.setAdapter(adapter);

        //View map = findViewById(R.id.MapFragment);
        //find search, location, search button

        SearchView ser = findViewById(R.id.searchView);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.MapFragment);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);   //this or this::OnMapReady
        //Button searchButton = findViewById(R.id.searchLocationMain);
        ser.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //mapFragment.getMapAsync(MainActivity.this);
                geocoder = new Geocoder(MainActivity.this);
                try {
                    locations =  geocoder.getFromLocationName(ser.getQuery().toString(), 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Address address=null;
                if(locations!=null) {
                    address = locations.get(0);
                    MarkerOptions markerOptions1 = null;
                    if (address != null) {
                        markerOptions1 = new MarkerOptions().position(new LatLng(address.getLatitude(), address.getLongitude())).title(address.getLocality());
                        googleMap.addMarker(markerOptions1);
                    }
                }
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        suggested_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                suggestedLocation suggested_location = (suggestedLocation) suggested_list.getItemAtPosition(position);
                String location_name = suggested_location.location_name;
                String state_name = suggested_location.state;
                ser.setQuery(location_name + "," + state_name,true);
                Log.i(TAG, "onItemClick: -----------------helloooooooooooooooooooooooooooooooo-----------------------------------------------------------------------------------------------------");
                Toast.makeText(MainActivity.this, "Position",   Toast.LENGTH_LONG).show();
                addMarker(location_name + "," + state_name);   //callOnClick() also works
            }
        });
    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.googleMap = googleMap;
        //updateLocationUI();
        //updateMarkers();

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
    void addMarker(String query){
        SearchView ser = findViewById(R.id.searchView);
        geocoder = new Geocoder(MainActivity.this);
        try {
            locations =  geocoder.getFromLocationName(ser.getQuery().toString(), 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Address address=null;
        if(locations!=null) {
            address = locations.get(0);
            MarkerOptions markerOptions1 = null;
            if (address != null) {
                markerOptions1 = new MarkerOptions().position(new LatLng(address.getLatitude(), address.getLongitude())).title(address.getLocality());
                googleMap.addMarker(markerOptions1);
            }
        }
    }
}