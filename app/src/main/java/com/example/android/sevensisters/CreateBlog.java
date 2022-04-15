package com.example.android.sevensisters;

import static android.provider.ContactsContract.Intents.Insert.ACTION;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class CreateBlog extends AppCompatActivity {

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
                String display_name = display_name_edit.getText().toString();
                String title = title_edit.getText().toString();
                String content = text_body_edit.getText().toString();
                if(display_name.equals("") || title.equals("") || content.equals("")) {
                    Toast toast = new Toast(CreateBlog.this);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setText("credentials incomplete");
                    return;
                }
                HashMap<String,String> map = new HashMap<>();
                map.put("display_name",display_name);
                map.put("title",title);
                String username = srp.getString("username",null);
                map.put("content",content);
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("blogs");
                ref.child(username);
                DatabaseReference dref = ref.child(username);
                dref.setValue(map);
                finish();
            }
        });
    }
}