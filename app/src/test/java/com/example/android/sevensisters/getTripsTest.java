package com.example.android.sevensisters;

import org.junit.Test;

import static org.junit.Assert.*;

import android.content.Context;

import com.example.android.sevensisters.data.MyDbHandler;
import com.example.android.sevensisters.data.Trip;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.List;

public class getTripsTest {
    @Test
    public void getAllTrips(){
        Context context=null;
        MyDbHandler dbHandler = new MyDbHandler(null);
        assertEquals(List.class,dbHandler.getAllTrips().getClass());
    }
}
