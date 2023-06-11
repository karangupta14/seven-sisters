package com.example.android.sevensisters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class BlogImagesAdapter extends ArrayAdapter<Uri> {
    public BlogImagesAdapter(Context context, ArrayList<Uri> objects){
        super(context,0,objects);
    }
    @Override
    @NonNull
    public View getView(int position, @Nullable View convertView,@NonNull ViewGroup parent){
        View listItem=convertView;
        if(listItem == null){
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.blog_image_view,parent,false);
        }
        Uri item = getItem(position);
        ImageView imageView = listItem.findViewById(R.id.image);
        // bitmap to bytearray to string to bytearray to bitmap
        //Bitmap bitmap = BitmapFactory.decodeByteArray(item,0,item.length);
        imageView.setImageURI(item);
        return listItem;
    }
}
