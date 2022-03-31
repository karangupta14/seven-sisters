package com.example.android.sevensisters;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.tabs.TabLayout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class HotelAdapter extends ArrayAdapter<hotel> {
    TabLayout.OnTabSelectedListener context;
    String state;
    public HotelAdapter(View.OnClickListener context, List<hotel> objects, String state){
        super((Context) context,0,objects);
        this.state = state;
    }
    public View getView(int position, @Nullable android.view.View convertView, @NonNull ViewGroup parent){
        View listItem = convertView;
        if(listItem == null){
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.hotel_layout,parent,false);
        }
        hotel currentHotel = getItem(position);
        TextView hotel_name = listItem.findViewById(R.id.hotel_name);
        TextView hotel_description = listItem.findViewById(R.id.hotel_description);
        String hot_name="" ; ///home/user/AndroidStudioProjects/SevenSisters
        String description="";
        String loc = String.valueOf(position+1);
        Log.i(TAG, "getView: ");
        InputStream file1 = null;
        try {
            file1 = getContext().getAssets().open("hotels/"+state+"/"+loc+"/Description.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader br = null;
        String temp;
        br = new BufferedReader(new InputStreamReader(file1));
        try {
            while( (temp = br.readLine()) != null)
            {
                description = description + temp ;
                Log.i(TAG, "getView: description read---------------------------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        InputStream file2 = null;

        try {
            file2 = getContext().getAssets().open("hotels/" + state + "/" + loc + "/name.txt");
        }catch (IOException e) {
            e.printStackTrace();
        }

        br = new BufferedReader(new InputStreamReader(file2));
        try {
            hot_name=br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //populating location name and location description
        hotel_name.setText(hot_name);
        hotel_description.setText(description);
        InputStream file3=null;
        try {
            file3 = getContext().getAssets().open("hotels/" + state + "/" + loc + "/Image.jpg");
        } catch (IOException e) {
            e.printStackTrace();
            Log.i(TAG, "getView: image not found---------------------------");
        }
        ImageView image = listItem.findViewById(R.id.hotel_image);
        Drawable d = Drawable.createFromStream(file3, null);
        image.setImageDrawable(d);
        return listItem;
    }
}
