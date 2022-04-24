package com.example.android.sevensisters;

import static android.content.ContentValues.TAG;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.android.sevensisters.data.MyDbHandler;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sdp_signup =  getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sdp_signup.edit();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
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
             //openOrCreateDatabase()
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
    }
}