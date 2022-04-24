package com.example.android.sevensisters;
import com.example.android.sevensisters.data.MyDbHandler;
import com.example.android.sevensisters.data.Trip;
import static org.junit.Assert.*;
import android.content.Context;
import org.junit.Test;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;

public class addTripTest {
    @Test
    public void addTrip() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        MyDbHandler dbHandler = new MyDbHandler(appContext);
        Trip t = new Trip("Assam", "Majuli Island,Dipor Bil,Hajo", "18-04-2022", "25-04-2022");
        boolean bool = dbHandler.addTrip(t);
        assertEquals(true, bool);
    }
}