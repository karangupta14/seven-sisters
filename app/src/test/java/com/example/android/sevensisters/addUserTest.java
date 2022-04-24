package com.example.android.sevensisters;

import static android.content.ContentValues.TAG;
import static org.junit.Assert.*;

import android.util.Log;

import org.junit.Test;
//import org.jupiter;


public class addUserTest {
    @Test
    public void addUser(){
        String output="demo";
        SignUp.addUser("demo_username","demo_password","demo_name","jadi.pujita@iiitg.ac.in","9812410853","bangalore");
        if(SignUp.userExist("demo_username"))
            output="true";
        else output="false";
        Log.i(TAG, "addUser: -----------" + output);
        assertEquals("true","true");
    }
    //@org.junit.jupiter.api.Test
    //void setMake(){
    //    fail();
    //}
}
