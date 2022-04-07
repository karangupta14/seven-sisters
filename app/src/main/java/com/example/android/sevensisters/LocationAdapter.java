package com.example.android.sevensisters;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import java.text.FieldPosition;
import java.util.List;

public class LocationAdapter extends ArrayAdapter<location> {

    TabLayout.OnTabSelectedListener context;
    String state;
    public LocationAdapter(Context context, List <location> objects, String state){
        super((Context) context,0,objects);
        this.state = state;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View listItem = convertView;
        if(listItem == null){
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.location_item,parent,false);
        }
        location currentLocation = getItem(position);
        TextView location_name = listItem.findViewById(R.id.location_name);
        TextView location_description = listItem.findViewById(R.id.location_description);
        String loc_name="" ; ///home/user/AndroidStudioProjects/SevenSisters
        String description="";
        String loc = String.valueOf(position+1);
        position+=1;
        Log.i(TAG, "getView: Location Number check-----------------"+loc);
        String temp;
        InputStream inputStream1=null;
        try {
            inputStream1 = getContext().getAssets().open("locations/"+state+"/"+position+"/Description.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //File file = new File("assets/locations/"+state+"/"+position+"/Description.txt");
        BufferedReader br = null;

        if (inputStream1 != null) {
            br = new BufferedReader(new InputStreamReader(inputStream1));
            Log.i(TAG, "getView: file exist--------------------------------------------------------");
        }
        else
            Log.i(TAG, "getView: file does not exist-------------------------------------------------");
        try {
            while( (temp = br.readLine()) != null)
            {
                description += temp;
            }
        } catch (IOException e) {
            Log.i(TAG, "getView: stack trace readline--------------------");
            e.printStackTrace();
        }
        location_description.setText(description);
        InputStream inputStream2=null;
        try {
            inputStream2 = getContext().getAssets().open("locations/"+state+"/"+position+"/Name.txt");
        } catch (IOException e) {
            e.printStackTrace();
            Log.i(TAG, "getView: file does not exist----------------------------");
        }
        try {
            br = new BufferedReader(new InputStreamReader(inputStream2));
            loc_name=br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //populating location name and location description
        location_name.setText(loc_name);
        int flag=1,im_no=1;
        //populating image list
        LinearLayout linearLayout = listItem.findViewById(R.id.location_image_list);
        while(flag==1){
            InputStream file = null;
            try {
                file = getContext().getAssets().open("locations/" + state + "/" + position + "/Image" + im_no + ".jpg");
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(file != null) {
                Drawable d = Drawable.createFromStream(file, null);
                if(im_no==1){
                    ImageView image = linearLayout.findViewById(R.id.image1);
                    image.setImageDrawable(d);
                }
                if(im_no==2){
                    ImageView image = linearLayout.findViewById(R.id.image2);
                    image.setImageDrawable(d);
                }
                if(im_no==3){
                    ImageView image = linearLayout.findViewById(R.id.image3);
                    image.setImageDrawable(d);
                }
                im_no++;
            }
            else{
                flag=0;
                Log.i(TAG, "getView: image is not there----------------------");
            }
        }
        return listItem;
    }
}
