package com.example.android.sevensisters;

import static android.content.ContentValues.TAG;

import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;

public class CreateBlog extends AppCompatActivity {
    ArrayList <Uri> encodedImageList = new ArrayList<Uri>();
    ActivityResultLauncher take_photo=registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                ListView listView = findViewById(R.id.blog_pic_list);
                Intent intent = result.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                //encodedImageList = new ArrayList<Uri>();
                if(intent != null && intent.getData() != null) {
                    Uri imageUri = intent.getData();
                    /*Log.i(TAG, "------------------single image got uri----------------: ");
                    Bitmap bitmap = null;
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/
                    encodedImageList.add(imageUri);// or image encoded
                }
                else if(intent != null && intent.getClipData() != null){
                    ClipData imagesClipData = intent.getClipData();
                    //Cursor cursor = getContentResolver().query(imagesClipData,filePathColumn,null,null,null);
                    Log.i(TAG, "------------------multiple image got uri----------------: ");
                    //ArrayList<Uri> ImagesUri = new ArrayList<Uri>();
                    for(int i=0; i<imagesClipData.getItemCount(); ++i){
                        ClipData.Item item = imagesClipData.getItemAt(i);
                        Uri uri = item.getUri();
                        /*Bitmap bitmap = null;
                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }*/
                        encodedImageList.add(uri);// or image encoded
                        //cursor.close();
                    }
                }
                ListView images_list = (ListView) findViewById(R.id.blog_pic_list);
                BlogImagesAdapter imageListAdapter = new BlogImagesAdapter(CreateBlog.this,encodedImageList);
                images_list.setAdapter(imageListAdapter);
            }
    );
    public void uploadPic(View v){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
        take_photo.launch(intent);
    }
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
        /*-----------------------Added profile icon on Action Bar-------------------------------------*/
        Drawable drawable = ContextCompat.getDrawable(this,R.drawable.action_bar_back);
        getSupportActionBar().setBackgroundDrawable(drawable);

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
                ArrayList <Uri> imagesAsUri = new ArrayList<Uri>();
                //ArrayList <byte[]> imagesAsByteArray = new ArrayList<byte[]>();
                /*if(encodedImageList.size() != 0){
                    Bitmap bitmap;
                    int n=encodedImageList.size();
                    for(int i=0; i<n; ++i){
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        //Bitmap bitmap1=encodedImageList.get(i);
                        //imagesAsBitmap.add(bitmap1);
                    }
                }*/
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
                        //ref.child(username);
                        HashMap<String,Uri> imagesHashmap = new HashMap<String,Uri>();
                        if(encodedImageList.size() != 0){
                            for(int i=0; i<imagesHashmap.size(); ++i){
                                imagesHashmap.put("image"+i,encodedImageList.get(i));
                            }
                        }
                        DatabaseReference textRef = ref.child(max[0]+"");
                        //DatabaseReference picsRef = ref.child("pictures").child(max[0]+"");
                        //if(encodedImageList.size() != 0)
                        textRef.setValue(map);//pictures still not included
                        //picsRef.setValue(imagesHashmap);           //hashmap containing every image as a bytearray
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
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
                take_photo.launch(intent);
                ListView images_list = (ListView) findViewById(R.id.blog_pic_list);
                BlogImagesAdapter imageListAdapter = new BlogImagesAdapter(this,encodedImageList);
                images_list.setAdapter(imageListAdapter);
            }
        });*/
    }
    /*ActivityResultLauncher take_photo=registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result-> {
                Intent data = result.getData();
                Uri selectedImageUri = null;
                String[] filePathColumn = { MediaStore.Images.Media.DATA };
                if(data != null)
                    selectedImageUri = data.getData();
                Cursor cursor = getContentResolver().query(selectedImageUri,filePathColumn,null,null);

                Bitmap selectedImageBitmap = null;
                try {
                    selectedImageBitmap
                            = MediaStore.Images.Media.getBitmap(
                            this.getContentResolver(),
                            selectedImageUri);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                ImageView imageView = findViewById(R.id.blog_pic_list);
                imageView.setImageBitmap(selectedImageBitmap);
            }
    );*/
}