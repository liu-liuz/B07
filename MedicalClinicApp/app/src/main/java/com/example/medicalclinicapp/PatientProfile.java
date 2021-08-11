package com.example.medicalclinicapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class PatientProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile);

        configureBackButton();
        getIncomingIntent();     //formats the information sent to PatientProfile
    }


    private void configureBackButton() {
        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void getIncomingIntent() {
        Log.d("PatientProfile", "getIncomingIntent: checking for incoming intents.");

        if (getIntent().hasExtra("appointment_info")) {
            Log.d("PatientProfile", "getIncomingIntent: found intent extras.");

            String information = getIntent().getStringExtra("appointment_info");
            TextView info = findViewById(R.id.patientInfoTextView);
            info.setText(information);

        }


    }

}