package com.example.android.sevensisters;
import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
@RunWith(AndroidJUnit4.class)
public class addUserTest {
    @Test
    public void addUser(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        String output="demo";
        SignUp.addUser("demo_username","demo_password","demo_name","jadi.pujita@iiitg.ac.in","9812410853","bangalore");
        if(SignUp.userExist("demo_username"))
            output="true";
        else output="false";
        assertEquals("true",output);
    }
}
