package com.example.android.sevensisters.data;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MyDbHandler extends SQLiteOpenHelper {
    public MyDbHandler (Context context){
        super(context, tripParams.DB_NAME,null,tripParams.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_trip_db = "CREATE TABLE " + tripParams.TABLE_NAME + " ( " + tripParams.TRIP_ID + " INTEGER PRIMARY KEY, " + tripParams.TABLE_COLUMN_STATE_NAME + " TEXT, "
        + tripParams.TABLE_COLUMN_LOCATION_LIST +" TEXT, " +tripParams.TABLE_COLUMN_START_DATE + " TEXT, " + tripParams.TABLE_COLUMN_END_DATE +" TEXT, "
        + tripParams.TABLE_COLUMN_REVIEW + " TEXT, "+ tripParams.TABLE_COLUMN_COMPLETE+ " TEXT" +" ) ";
        Log.d(TAG, "onCreate: "+ create_trip_db + "is being run------------------------------");
        db.execSQL(create_trip_db);

        /*String create_picture_db = "CREATE TABLE " + pictureParams.TABLE_NAME + " ( " + pictureParams.TABLE_COLUMN_TRIP_ID + " INTEGER, " + pictureParams.TABLE_COLUMN_PICTURE_ID + " INTEGER PRIMARY KEY, "
        + pictureParams.TABLE_COLUMN_PICTURE_BLOB + " BLOB "+ ")";
        Log.d(TAG, "onCreate: "+ create_picture_db + "is being run");
        db.execSQL(create_picture_db);*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void addTrip(Trip row){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(tripParams.TABLE_COLUMN_COMPLETE,row.getTrip_id());
        values.put(tripParams.TABLE_COLUMN_START_DATE,row.getStart_date());
        values.put(tripParams.TABLE_COLUMN_STATE_NAME,row.getState_name());
        values.put(tripParams.TABLE_COLUMN_END_DATE,row.getEnd_date());
        values.put(tripParams.TABLE_COLUMN_LOCATION_LIST,row.getLocation_list());
        database.insert(tripParams.TABLE_NAME,null,values);
        Log.d(TAG, "addTrip: trip object successfully inserted");
        //values.put(tripParams.TABLE_COLUMN_REVIEW,row.getTrip_id());
        database.close();
    }
    public void addPicture(Picture row){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(pictureParams.TABLE_COLUMN_TRIP_ID,row.getTrip_id());
        values.put(pictureParams.TABLE_COLUMN_PICTURE_ID,row.getPicture_id());
        values.put(pictureParams.TABLE_COLUMN_PICTURE_BLOB,row.getPicture_binary());
        database.insert(pictureParams.TABLE_NAME,null,values);
    }
    public List<Trip> getAllTrips(){
        List<Trip> trip_list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + tripParams.TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                Trip row = new Trip();
                row.setTrip_id(Integer.parseInt(cursor.getString(0)));
                row.setState_name(cursor.getString(1));
                row.setLocation_list(cursor.getString(2));
                row.setStart_date(cursor.getString(2));
                row.setEnd_date(cursor.getString(3));
                trip_list.add(row);
            }while(cursor.moveToNext());
        }
        return trip_list;
    }
}
