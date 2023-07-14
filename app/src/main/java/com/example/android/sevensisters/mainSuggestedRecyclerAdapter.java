package com.example.android.sevensisters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class mainSuggestedRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<suggestedLocation> locations;

    public mainSuggestedRecyclerAdapter(Context context, ArrayList<suggestedLocation> locations) {
        this.context = context;
        this.locations = locations;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.suggested_location,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        suggestedLocation location = locations.get(position);
        TextView textView = holder.itemView.findViewById(R.id.location_name);
        textView.setText(location.location_name +", "+location.state);
        ImageView imageView = holder.itemView.findViewById(R.id.imageView);

    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView location_name;
        ImageView imageView;
        public ViewHolder(@NonNull View view){
            super(view);
            this.location_name = view.findViewById(R.id.location_name);
            this.imageView = view.findViewById(R.id.imageView);
        }

        @Override
        public void onClick(View view) {

        }
    }

}
