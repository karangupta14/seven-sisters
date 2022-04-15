package com.example.android.sevensisters;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        EditText username_sign_in = findViewById(R.id.username_sign_in);
        EditText password_sign_in = findViewById(R.id.password_sign_in);
        Button login = findViewById(R.id.sign_in_button);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = username_sign_in.getText().toString();
                String password = password_sign_in.getText().toString();
                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild(username)){// && snapshot.child(username).child("password").equals(password)){
                            SharedPreferences srp = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = srp.edit();
                            Toast toast = new Toast(SignIn.this);
                            toast.setDuration(Toast.LENGTH_LONG);
                            //toast.setText("after map");
                            //toast.show();
                            //ArrayList<String>list = new ArrayList<>();
                            //HashMap<String,String> map = snapshot.child(username).getValue(HashMap.class);
                            //for(DataSnapshot snapshot1 : snapshot.getChildren()){
                                //map.put(snapshot1.getKey().toString(),snapshot1.getValue(String.class));
                                //list.add(snapshot1.getValue(String.class));
                            //}
                            //toast.setText("after hashmap");
                            //toast.show();
                            String get_password = snapshot.child(username).child("password").getValue(String.class);
                            if(!password.equals(get_password)){
                                toast.setDuration(Toast.LENGTH_LONG);
                                toast.setText("incorrect password");
                                toast.show();
                                return;
                            }

                            editor.putString("username",username).apply();
                            editor.putString("password",password).apply();
                            String name = snapshot.child(username).child("name").getValue(String.class);
                            String phone = snapshot.child(username).child("phone").getValue(String.class);
                            String email = snapshot.child(username).child("email").getValue(String.class);
                            String city = snapshot.child(username).child("city").getValue(String.class);
                            editor.putString("name",name).apply();
                            editor.putString("user_phone",phone).apply();
                            editor.putString("user_email",email).apply();
                            editor.putString("person_city",city).apply();
                            editor.putBoolean("logged_in",true).apply();
                            finish();

                        }
                        else{
                            Toast toast = new Toast(SignIn.this);
                            toast.setText("username incorrect");
                            toast.setDuration(Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
        TextView signup_link = findViewById(R.id.sign_up_link);
        signup_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup = new Intent(SignIn.this,SignUp.class);
                startActivity(signup);
            }
        });
    }
}