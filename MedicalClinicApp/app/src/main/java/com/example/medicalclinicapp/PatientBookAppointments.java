package com.example.medicalclinicapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class PatientBookAppointments extends AppCompatActivity {

    private static final String TAG = "PatientBookAppointment";       //added this line for debugging
    private ArrayList<String> mDoctorNames  = new ArrayList<>();   //added variables - the same one that we had in the adapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_book_appointments);

        Log.d(TAG, "onCreate: started.");
        //populating the array with strings for testing
        //do this from the database after
        mDoctorNames.add("Doctor Name: Dr. Mary Ford");
        mDoctorNames.add("Doctor Name: Dr. Jane Simcoe");
        mDoctorNames.add("Doctor Name: Dr. Frank Grey");
        mDoctorNames.add("Doctor Name: Dr. Kevin Becker");
        mDoctorNames.add("Doctor Name: Dr. Tim Becker");
        mDoctorNames.add("Doctor Name: Dr. Alex Mathew");
        mDoctorNames.add("Doctor Name: Dr. Mary Herbert");
        mDoctorNames.add("Doctor Name: Dr. Samantha Rice");
        mDoctorNames.add("Doctor Name: Dr. Joel Daniel");
        mDoctorNames.add("Doctor Name: Dr. Dylan Finnegan");
        mDoctorNames.add("Doctor Name: Dr. Kylie Armstrong");
        mDoctorNames.add("Doctor Name: Dr. Mary Crawford");
        mDoctorNames.add("Doctor Name: Dr. Janet Simcoe");
        mDoctorNames.add("Doctor Name: Dr. Franklin White");
        mDoctorNames.add("Doctor Name: Dr. Kevin Decker");
        mDoctorNames.add("Doctor Name: Dr. Tom Decker");
        mDoctorNames.add("Doctor Name: Dr. Alexis Sanchez");
        mDoctorNames.add("Doctor Name: Dr. Marylin Hernandez");
        mDoctorNames.add("Doctor Name: Dr. Sam Richard");
        mDoctorNames.add("Doctor Name: Dr. Joseph Clint");
        mDoctorNames.add("Doctor Name: Dr. Devin Finn");
        mDoctorNames.add("Doctor Name: Dr. Kyle Alvarez");
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
        RecyclerView recyclerView = findViewById(R.id.listDoctors);
        //parentLayout is from recyclerview_row.xml
        //upcomingAppointments is from activity_main.xml

        //create RecyclerViewAdapter object + passing in the dataset and the context to the adapter
        Log.d(TAG, "creating RecyclerViewAdapter object");
        //took out 'this' as the last argument
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mDoctorNames);

        Log.d(TAG, "adapter created");
        recyclerView.setAdapter(adapter); //passing a null variable to this line
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}