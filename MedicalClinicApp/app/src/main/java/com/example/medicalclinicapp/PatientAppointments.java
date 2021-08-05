package com.example.medicalclinicapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class PatientAppointments extends AppCompatActivity {

    private static final String TAG = "PatientAppointment";       //added this line for debugging
    private ArrayList<String> mAppointmentNames  = new ArrayList<>();   //added variables - the same one that we had in the adapter
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_appointments);

        Log.d(TAG, "onCreate: started.");
        //populating the array with strings for testing
        //do this from the database after
        mAppointmentNames.add("Appointment ID: 23455 \nPatient Name:  \nDoctorName: \nAppointmentDate:");
        mAppointmentNames.add("Appointment ID: 45372 \nPatient Name:  \nDoctorName: \nAppointmentDate:");
        mAppointmentNames.add("Appointment ID: 89765 \nPatient Name:  \nDoctorName: \nAppointmentDate:");
        mAppointmentNames.add("Appointment ID: 45376 \nPatient Name:  \nDoctorName: \nAppointmentDate:");
        mAppointmentNames.add("Appointment ID: 38458 \nPatient Name:  \nDoctorName: \nAppointmentDate:");
        mAppointmentNames.add("Appointment ID: 37654 \nPatient Name:  \nDoctorName: \nAppointmentDate:");
        mAppointmentNames.add("Appointment ID: 37546 \nPatient Name:  \nDoctorName: \nAppointmentDate:");
        mAppointmentNames.add("Appointment ID: 37463 \nPatient Name:  \nDoctorName: \nAppointmentDate:");
        mAppointmentNames.add("Appointment ID: 23746 \nPatient Name:  \nDoctorName: \nAppointmentDate:");
        mAppointmentNames.add("Appointment ID: 32436 \nPatient Name:  \nDoctorName: \nAppointmentDate:");
        //initiating the recycler view
        initRecyclerView();

        configureBackButton();

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

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init recyclerview");
        RecyclerView recyclerView = findViewById(R.id.upcomingAppointments);
        //parentLayout is from recyclerview_row.xml
        //upcomingAppointments is from activity_main.xml

        //create RecyclerViewAdapter object + passing in the dataset and the context to the adapter
        Log.d(TAG, "creating RecyclerViewAdapter object");
        //took out 'this' as the last argument
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mAppointmentNames);

        Log.d(TAG, "adapter created");
        recyclerView.setAdapter(adapter); //passing a null variable to this line
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}