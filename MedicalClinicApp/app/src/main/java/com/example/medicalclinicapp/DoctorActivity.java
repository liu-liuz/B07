package com.example.medicalclinicapp;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class DoctorActivity extends AppCompatActivity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_activity);

//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
//        User user = new User("user1","12345","Doctor");
//        ref.child("users").child("u1").setValue(user);
    }
}
