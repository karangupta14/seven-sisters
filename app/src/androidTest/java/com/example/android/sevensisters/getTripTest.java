package com.example.android.sevensisters;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

import com.example.android.sevensisters.data.MyDbHandler;
import com.example.android.sevensisters.data.Trip;

import java.util.ArrayList;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class getTripTest {
    @Test
    public void getTrip(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        MyDbHandler dbHandler = new MyDbHandler(appContext);
        assertEquals(ArrayList.class,dbHandler.getAllTrips().getClass());
    }
}
