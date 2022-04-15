package com.example.android.sevensisters;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

public class BlogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);
        FloatingActionButton floating = findViewById(R.id.add_blog_button);
        SharedPreferences srp = getSharedPreferences("MyPREFERENCES",MODE_PRIVATE);
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
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("blogs");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList <Blog> blogs = new ArrayList<Blog>();
                String title = snapshot.child(srp.getString("username",null)).child("title").getValue(String.class);
                String display_name = snapshot.child(srp.getString("username",null)).child("display_name").getValue(String.class);
                String content = snapshot.child(srp.getString("username",null)).child("content").getValue(String.class);
                Blog blog  = new Blog(title,display_name,content);
                blogs.add(blog);
                BlogAdapter adapter = new BlogAdapter(BlogActivity.this,blogs);
                blog_list.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}