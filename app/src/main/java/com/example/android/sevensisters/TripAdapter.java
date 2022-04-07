package com.example.android.sevensisters;


import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.sevensisters.data.Trip;

import java.util.ArrayList;
import java.util.List;

public class TripAdapter extends ArrayAdapter<Trip> {

    String locations;
    String state;
    String tripId;
    String start_date;
    String end_date;
    public TripAdapter(Context context, List<Trip> objects){
        super(context,0,objects);

    }
    @Override
    @NonNull
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View listItem = convertView;
        if(listItem == null){
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.history_item,parent,false);
        }
        Trip trip_object = getItem(position);
        TextView destination = listItem.findViewById(R.id.trip_state);
        String state = trip_object.getState_name();
        TextView date = listItem.findViewById(R.id.time_interval);
        destination.setText(trip_object.getState_name());
        Log.i(TAG, "getView: state name and date seted---------------------");
        date.setText(trip_object.getStart_date());// + " to " + trip_object.getEnd_date());
        Log.i(TAG, "getView: date seted---------------------------------------");
        ImageView destinationPic = listItem.findViewById(R.id.destination_pic);
        if(state.equals("Arunachal Pradesh"))
            destinationPic.setImageResource(R.drawable.arunachal_discover);
        else if(state.equals("Assam"))
            destinationPic.setImageResource(R.drawable.assam_discover);
        else if(state.equals("Manipur"))
            destinationPic.setImageResource(R.drawable.manipur_discover);
        else if(state.equals("Meghalaya"))
            destinationPic.setImageResource(R.drawable.meghalaya_discover);
        else if(state.equals("Mizoram"))
            destinationPic.setImageResource(R.drawable.mizoram_discover);
        else if(state.equals("Nagaland"))
            destinationPic.setImageResource(R.drawable.nagaland_state);
        else if(state.equals("Tripura"))
            destinationPic.setImageResource(R.drawable.tripura_state);

        return listItem;
    }
}
