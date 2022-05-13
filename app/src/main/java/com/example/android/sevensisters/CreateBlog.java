package com.example.android.sevensisters;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.*;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.*;//AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class CreateBlog extends AppCompatActivity {

    ActivityResultLauncher<String> take_photo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_blog);
        EditText display_name_edit = findViewById(R.id.create_blog_display_name);
        EditText title_edit = findViewById(R.id.create_blog_title);
        EditText text_body_edit = findViewById(R.id.blog_text_body);


        Button post_button = findViewById(R.id.post_button);
        Intent select_pics = new Intent(Intent.ACTION_GET_CONTENT);
        //select_pics.setType("image/*");
        SharedPreferences srp = getSharedPreferences("MyPREFERENCES",MODE_PRIVATE);
        post_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int[] max = new int[1];
                String display_name = display_name_edit.getText().toString();
                String title = title_edit.getText().toString();
                String content = text_body_edit.getText().toString();
                if(display_name.equals("") || title.equals("") ){
                    Toast toast = new Toast(CreateBlog.this);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setText("credentials incomplete");
                    return;
                }
                if(content.length() <= 150){
                    Toast toast = new Toast(CreateBlog.this);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setText("length of blog should at least be 150 characters");
                    return;
                }
                HashMap<String,String> map = new HashMap<>();
                map.put("display_name",display_name);
                map.put("title",title);
                String username = srp.getString("username",null);
                map.put("content",content);
                map.put("username",username);
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("blogs");
                ref.orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        max[0] =0;
                        for(DataSnapshot snapshoti : snapshot.getChildren()){
                            if(Integer.parseInt(snapshoti.getKey()) > max[0]){
                                max[0] = Integer.parseInt(snapshoti.getKey());
                            }
                        }
                        max[0]=max[0]+1;
                        ref.child(username);
                        DatabaseReference dref = ref.child(max[0]+"");
                        dref.setValue(map);
                        finish();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
        Button upload_pic = findViewById(R.id.upload_pic_button);
        /*upload_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                take_photo=registerForActivityResult(
                        new ActivityResultContracts.GetContent(),
                        new ActivityResultCallback<Uri>() {
                            @Override
                            public void onActivityResult(Uri result) {
*
                            }
                        }
                );
            }
        });*/
    }
}