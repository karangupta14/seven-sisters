package com.example.android.sevensisters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class BlogAdapter extends ArrayAdapter<Blog> {

    public BlogAdapter(Context context, List <Blog> objects){
        super(context,0,objects);
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
        return listItem;
    }
}
