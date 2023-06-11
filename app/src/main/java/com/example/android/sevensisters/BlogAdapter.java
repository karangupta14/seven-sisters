package com.example.android.sevensisters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BlogAdapter extends ArrayAdapter<Blog> {
    Context context;
    public BlogAdapter(Context context, List <Blog> objects){
        super(context,0,objects);
        this.context = context;
    }
    @Override
    public View getView(int position , @Nullable View convertView , @NonNull ViewGroup parent){
        View listItem = convertView;
        if(convertView == null){
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.blog_item,parent,false);
        }
        Blog blog = getItem(position);
        TextView title = listItem.findViewById(R.id.title);
        TextView display_name = listItem.findViewById(R.id.display_name);
        TextView content = listItem.findViewById(R.id.content);
        title.setText(blog.title);
        display_name.setText(blog.display_name);
        content.setText(blog.content);
        //ArrayList <byte[]> imagesByteArray = blog.getBlogImageList();
        ArrayList <Uri>imagesBitmapArray = blog.getBlogImageList();
        //byte array to bitmap
        byte[] a = null;
        /*for(int i = 0; i<imagesByteArray.size(); ++i){
            a = imagesByteArray.get(i);
            Bitmap bitmap = BitmapFactory.decodeByteArray(a,0,a.length);
            imagesBitmapArray.add(bitmap);
        }*/

        ListView picsList = (ListView) listItem.findViewById(R.id.blog_pic_list);
        //BlogImagesAdapter adapter = new BlogImagesAdapter(context,imagesBitmapArray);
        //picsList.setAdapter(adapter);
        return listItem;
    }
}
