package com.example.android.sevensisters;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BlogActivity extends AppCompatActivity {
    ArrayList<HashMap<String,Bitmap>> imagesMaps;
    ArrayList <Blog> blogs = new ArrayList<Blog>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);
        FloatingActionButton floating = findViewById(R.id.add_blog_button);

        Drawable drawable = ContextCompat.getDrawable(this,R.drawable.action_bar_back);
        getSupportActionBar().setBackgroundDrawable(drawable);

        SharedPreferences srp = getSharedPreferences("MyPREFERENCES",MODE_PRIVATE);
        /* Adding profile icon on Action Bar*/
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
        /*------------------------------------------------------------*/
        floating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean login = srp.getBoolean("logged_in",false);
                if(!login){
                    Toast toast = new Toast(BlogActivity.this);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setText("first Login");
                    return;
                }
                Intent createBlog = new Intent(BlogActivity.this,CreateBlog.class);
                startActivity(createBlog);
                overridePendingTransition(0,0);
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
        View home = findViewById(R.id.home_icon);
        Intent homeI = new Intent(this,MainActivity.class);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(homeI);
                overridePendingTransition(0, 0);
            }
        });
        ListView blog_list = findViewById(R.id.blog_list);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int itr=0;
                for(DataSnapshot snapshot1 : snapshot.child("blogs").getChildren()){
                    String username = snapshot1.child("username").getValue(String.class);
                    String title = snapshot1.child("title").getValue(String.class);
                    String display_name = snapshot1.child("display_name").getValue(String.class);
                    String content = snapshot1.child("content").getValue(String.class);

                    Blog blog = new Blog(username, title, display_name, content);
                    blogs.add(blog);
                }
                /*for(DataSnapshot snapshot1 : snapshot.child("pictures").getChildren()){
                    HashMap<String,Bitmap> imageMap = snapshot1.getValue(HashMap.class);
                    imagesMaps.add(imageMap);
                }
                for(int i=0; i<blogs.size(); ++i){
                    HashMap<String,Bitmap> imageMap = imagesMaps.get(i);
                    ArrayList<Bitmap> imageList = new ArrayList<Bitmap>();
                    for(int j=0; j<imageMap.size(); ++j){
                        imageList.add(imageMap.get("image"+j));
                    }
                    //blogs.get(i).setBlogImageList(imageList);
                }*/
                BlogAdapter adapter = new BlogAdapter(BlogActivity.this,blogs);
                blog_list.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        DatabaseReference picRef = FirebaseDatabase.getInstance().getReference("pictures");
        /*picRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                imagesMaps = new ArrayList<HashMap<String,Bitmap>>();
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    HashMap<String,Bitmap> map = snapshot1.getValue(HashMap.class);
                    imagesMaps.add(map);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
    }
}