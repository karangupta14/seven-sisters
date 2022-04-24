package com.example.android.sevensisters;
import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import com.example.android.sevensisters.data.MyDbHandler;
import com.example.android.sevensisters.data.Trip;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
public class addTripTest {
    @Test
    public void addTrip(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        MyDbHandler dbHandler = new MyDbHandler(appContext);
        Trip t = new Trip("Assam","Majuli Island,Dipor Bil,Hajo","18-04-2022","25-04-2022");
        boolean bool = dbHandler.addTrip(t) ;
        assertEquals(true,bool);
    }
}
