package com.example.android.sevensisters.data;

public class Trip {
    public int trip_id;
    public String start_date;
    public String end_date;
    public String state_name;
    public String location_list;
    public boolean complete;

    public Trip(){

    }
    public Trip(String state_name, String location_list , String start_date, String end_date){
        //this.trip_id=trip_id;
        this.start_date=start_date;
        this.end_date=end_date;
        this.location_list=location_list;
        this.state_name=state_name;
    }
    public int getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public String getLocation_list() {
        return location_list;
    }

    public void setLocation_list(String location_list) {
        this.location_list = location_list;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}
