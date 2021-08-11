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




            /*final String[] name = new String[1];
            final String[] gender = new String[1];
            final String[] dob = new String[1];
            final List<String>[] past = new List<String>[1];
            final List<String>[] upcoming = new List<String>[1];
            final List<String>[] docs = new List<String>[1];*/


            //find appointment ID
            //String information = getIntent().getStringExtra("appointment_info");
            int index = information.indexOf(")");
            String id = information.substring(index + 1);
            System.out.println("The appointment id is:" + id);


            //extract additional information from Firebase using appointment id


            FirebaseDatabase.getInstance().getReference().child("appointments").addListenerForSingleValueEvent(new ValueEventListener() {

                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Appointment appt = snapshot.getValue(Appointment.class);

                        if (appt.getId() == id) {
                            Log.i("PatientInfo", appt.getPatient().getName());
                                /*name[0] = appt.getPatient().getName();
                                gender[0] = appt.getPatient().getGender();
                                dob[0] = appt.getPatient().getBirthdate();
                                past[0] = appt.getPatient().getPrevious();
                                upcoming[0] = appt.getPatient().getUpcoming();
                                docs[0] = appt.getPatient().getVisited();*/
//
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull @NotNull DatabaseError error) {
                }
            });



            /*
            //storing data into local variables
            String name = getIntent().getStringExtra("image_url");
            String gender = getIntent().getStringExtra("image_name");
            String dob = getIntent().getStringExtra("image_name");

            //find the appropriate view by ID and set its text to one collected above
            TextView patient_name = findViewById(R.id.patient_name);
            patient_name.setText(name);

            TextView patient_gender = findViewById(R.id.patient_gender);
            patient_gender.setText(gender);

            TextView patient_birthday = findViewById(R.id.patient_birthday);
            patient_name.setText(dob);
            */
        }


    }

}