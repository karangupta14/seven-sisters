package com.example.android.sevensisters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;

public class suggestedAdapter extends ArrayAdapter {
    public suggestedAdapter(@NonNull Context context, List <suggestedLocation> objects) {
        super(context, 0,objects);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View listItem = convertView;
        if(listItem == null)
             listItem =  LayoutInflater.from(getContext()).inflate(R.layout.suggested_location, parent, false);
        suggestedLocation item = (suggestedLocation) getItem(position);
        Random random = new Random();
        int ran = random.nextInt(8);
        ran+=1;
        InputStream stream_state = null;
        try {
            stream_state = getContext().getAssets().open("locations/" + item.state + "/" + ran + "/Name.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader bfr = new BufferedReader(new InputStreamReader(stream_state));
        String location = null;
        try {
            location = bfr.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        location += ", " + item.state;
        TextView textView = listItem.findViewById(R.id.location_name);
        textView.setText(location);
        textView.setTextColor(Color.parseColor("#FFFFFF"));
        //now set image drawable
        InputStream stream_picture = null;
        try {
            stream_picture = getContext().getAssets().open("locations/" + item.state + "/" + ran + "/Image1.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Drawable image_drawable = Drawable.createFromStream(stream_picture,null);
        ImageView location_image = listItem.findViewById(R.id.location_image);
        location_image.setImageDrawable(image_drawable);
        return listItem;
    }
}
