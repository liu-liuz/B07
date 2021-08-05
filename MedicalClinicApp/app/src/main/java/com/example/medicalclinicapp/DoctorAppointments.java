package com.example.medicalclinicapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;

public class DoctorAppointments extends AppCompatActivity {

    private static final String TAG = "DoctorAppointment";       //added this line for debugging
    private ArrayList<String> mAppointmentNames  = new ArrayList<>();   //added variables - the same one that we had in the adapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_appointments);

        Log.d(TAG, "onCreate: started.");
        //populating the array with strings for testing
        //do this from the database after
        mAppointmentNames.add("Appointment 1 \nPatient Information:");
        mAppointmentNames.add("Appointment 2 \nPatient Information:");
        mAppointmentNames.add("Appointment 3 \nPatient Information:");
        mAppointmentNames.add("Appointment 4 \nPatient Information:");
        mAppointmentNames.add("Appointment 5 \nPatient Information:");
        mAppointmentNames.add("Appointment 6 \nPatient Information:");
        mAppointmentNames.add("Appointment 7 \nPatient Information:");
        mAppointmentNames.add("Appointment 8 \nPatient Information:");
        mAppointmentNames.add("Appointment 9 \nPatient Information:");
        mAppointmentNames.add("Appointment 10 \nPatient Information:");
        mAppointmentNames.add("Appointment 11 \nPatient Information:");
        mAppointmentNames.add("Appointment 12 \nPatient Information:");
        mAppointmentNames.add("Appointment 13 \nPatient Information:");
        mAppointmentNames.add("Appointment 14 \nPatient Information:");
        mAppointmentNames.add("Appointment 15 \nPatient Information:");
        //initiating the recycler view
        initRecyclerView();

        configureBackButton();

    }

    private void initRecyclerView(){
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

    private void configureBackButton(){
        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}