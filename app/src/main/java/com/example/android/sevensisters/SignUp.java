package com.example.android.sevensisters;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText$InspectionCompanion;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {
    public  static  class User
    {
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
    public static void addUser(String username_input, String password_input, String name_input, String email_input, String phone_input, String city_input){
        DatabaseReference dbrf = FirebaseDatabase.getInstance().getReference();
        HashMap<String,String> user_data = new HashMap<String,String>();
        user_data.put("name",name_input.toString());
        user_data.put("email",email_input.toString());
        user_data.put("phone",phone_input.toString());
        user_data.put("password",password_input.toString());
        user_data.put("city",city_input.toString());
        dbrf.child("users").child(username_input.toString());
        DatabaseReference userKeyRef = dbrf.child("users").child(username_input.toString());
        userKeyRef.setValue(user_data);
    }
    public static Boolean userExist(String username){
        Boolean[] flag = {false};
        DatabaseReference dbrf = FirebaseDatabase.getInstance().getReference("users");
        dbrf.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(username)){
                    flag[0] =true;
                }
                return;
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return true;
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
        Drawable drawable = ContextCompat.getDrawable(this,R.drawable.action_bar_back);
        getSupportActionBar().setBackgroundDrawable(drawable);
        FirebaseDatabase fdb = FirebaseDatabase.getInstance();
        DatabaseReference dbrf = fdb.getReference();//"https://sevensisters-8bd65-default-rtdb.firebaseio.com/"
        //DatabaseReference user_details = dbrf.child("user_details");
        //user_details.child(username_input.toString()).setValue(new User(name_input.toString(),email_input.toString(),username_input.toString(),password_input.toString(),phone_input.toString()));
        Button sign_up = findViewById(R.id.sign_up_button);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int flag[]=new int[1];
                flag[0]=1;
                Editable name_input = name.getText();
                Editable email_input = email.getText();
                Editable username_input = username.getText();
                Editable password_input = password.getText();
                Editable phone_input = phone.getText();
                Editable city_input = city.getText();
                // check for username, email-d and password validity
                if(username_input.toString().length() <=4){
                    Toast toast = new Toast(SignUp.this);
                    toast.setText("username should be at least 5 characters long");
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }
                if(password_input.toString().length() < 4){
                    Toast toast = new Toast(SignUp.this);
                    toast.setText("password should be at least 4 characters long");
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }
                if(!(email_input.toString().endsWith("gmail.com") || email_input.toString().endsWith("hotmail.com") ||email_input.toString().endsWith("rediffmail.com"))){
                    Toast toast = new Toast(SignUp.this);
                    toast.setText("enter a valid email address that ends with \"gmail.com\" or \"hotmail.com\" or \"rediffmail.com\"");
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }
                SharedPreferences sdp_signup =  getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sdp_signup.edit();
                dbrf.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild(username_input.toString())){
                            Toast toast = new Toast(SignUp.this);
                            toast.setText("Username unavailable!");
                            toast.setDuration(Toast.LENGTH_LONG);
                            toast.show();
                            flag[0]=0;
                        }
                        else{
                            editor.putString("name",name_input.toString()).apply();
                            editor.putString("username",username_input.toString()).apply();
                            editor.putString("user_email",email_input.toString()).apply();
                            editor.putString("user_phone",phone_input.toString()).apply();
                            editor.putString("password",password_input.toString()).apply();
                            editor.putString("person_city",city_input.toString()).apply();
                            editor.putBoolean("logged_in",true).apply();
                            editor.apply();

                            HashMap<String,String> user_data = new HashMap<String,String>();
                            user_data.put("name",name_input.toString());
                            user_data.put("email",email_input.toString());
                            user_data.put("phone",phone_input.toString());
                            user_data.put("password",password_input.toString());
                            user_data.put("city",city_input.toString());
                            dbrf.child("users").child(username_input.toString());
                            DatabaseReference userKeyRef = dbrf.child("users").child(username_input.toString());
                            userKeyRef.setValue(user_data);
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                if(flag[0]==0)
                    return;

            }
        });

        TextView login_link = findViewById(R.id.sign_in_link);
        login_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(SignUp.this,SignIn.class);
                startActivity(login);
            }
        });
    }
}