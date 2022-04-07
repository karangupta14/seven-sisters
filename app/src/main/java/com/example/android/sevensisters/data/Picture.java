package com.example.android.sevensisters.data;

import java.sql.Blob;

public class Picture {
    public int picture_id;
    public int trip_id;
    public byte[] picture_binary;

    public int getPicture_id() {
        return picture_id;
    }

    public void setPicture_id(int picture_id) {
        this.picture_id = picture_id;
    }

    public int getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }

    public byte[] getPicture_binary() {
        return picture_binary;
    }

    public void setPicture_binary(byte[] picture_binary) {
        this.picture_binary = picture_binary;
    }
}
