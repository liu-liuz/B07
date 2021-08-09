package com.example.medicalclinicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DoctorAppointments extends AppCompatActivity {
    private static final String TAG = "DoctorAppointment";       //added this line for debugging
    private ArrayList<String> mAppointmentNames  = new ArrayList<>();   //added variables - the same one that we had in the adapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_appointments);

        Log.d(TAG, "onCreate: started.");
        User this_user = (User)getIntent().getSerializableExtra("this_user");
        //System.out.println(this_user.getDoctorAccount().getName());

        FirebaseDatabase.getInstance().getReference().child("appointments").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Appointment appt = snapshot.getValue(Appointment.class);
                    Date currentDate = Calendar.getInstance().getTime();
                    if (appt.getDoctor().getName().equals(this_user.getDoctorAccount().getName())){
                        if ((appt.getDate().compareTo(currentDate) > 0) || (appt.getDate().compareTo(currentDate) == 0)){
                            mAppointmentNames.add(appt.getPatient().getName() + " at "+ appt.getDate());
                        }
                    }
                    //System.out.println(appt.getPatient().getName());
                    initRecyclerView();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadAppointment:onCancelled", databaseError.toException());
            }
        });

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