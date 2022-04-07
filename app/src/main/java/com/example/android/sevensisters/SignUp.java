package com.example.android.sevensisters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText$InspectionCompanion;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    public  static  class User{
        public  String name;
        public  String email;
        public  String username;
        public  String password;
        public  String phone;

        public  User(String name, String email,String username,String password,String phone)
        {
            this.name = name;
            this.email = email;
            this.username = username;
            this.password = password;
            this.phone = phone;
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        EditText name = findViewById(R.id.name_signup);
        EditText email = findViewById(R.id.email_signup);
        EditText username = findViewById(R.id.username_signup);
        EditText password = findViewById(R.id.password_signup);
        EditText phone = findViewById(R.id.phone_signup);
        EditText city = findViewById(R.id.location_signup);

        FirebaseDatabase fdb = FirebaseDatabase.getInstance();
        //DatabaseReference dbrf = fdb.getReference("https://sevensisters-8bd65-default-rtdb.firebaseio.com/");
        //DatabaseReference user_details = dbrf.child("user_details");
        //user_details.child(username_input.toString()).setValue(new User(name_input.toString(),email_input.toString(),username_input.toString(),password_input.toString(),phone_input.toString()));
        Button sign_up = findViewById(R.id.sign_up_button);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editable name_input = name.getText();
                Editable email_input = email.getText();
                Editable username_input = username.getText();
                Editable password_input = password.getText();
                Editable phone_input = phone.getText();
                Editable city_input = city.getText();
                SharedPreferences sdp_signup =  getSharedPreferences("MyPREFERENCES", Context.MODE_MULTI_PROCESS);
                SharedPreferences.Editor editor = sdp_signup.edit();
                editor.putString("name",name_input.toString()).apply();
                editor.putString("username",username_input.toString()).apply();
                editor.putString("user_email",email_input.toString()).apply();
                editor.putString("user_phone",phone_input.toString()).apply();
                editor.putString("password",password_input.toString()).apply();
                editor.putString("person_city",city_input.toString()).apply();
                editor.apply();
                finish();
            }
        });
    }
}