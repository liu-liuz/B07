package com.example.medicalclinicapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DoctorPastAppointments extends AppCompatActivity {

    private static final String TAG = "DoctorPastAppointment";       //added this line for debugging
    private ArrayList<Appointment> mAppointment  = new ArrayList<>();
    private ArrayList<String> mAppointmentNames  = new ArrayList<>();   //added variables - the same one that we had in the adapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_past_appointments);
        User this_user = (User)getIntent().getSerializableExtra("this_user");

        Log.d(TAG, "onCreate: started.");
        FirebaseDatabase.getInstance().getReference().child("appointments").addListenerForSingleValueEvent(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Appointment appt = snapshot.getValue(Appointment.class);
                    Date currentDate = Calendar.getInstance().getTime();
                    if (appt.getDoctor().getName().equals(this_user.getDoctorAccount().getName())){
                        if (appt.getDate().compareTo(currentDate) < 0){
                            mAppointment.add(appt);
                        }
                    }
                }
                mAppointment.sort(Comparator.comparing(Appointment::getDate).reversed());
                for(Appointment a: mAppointment){
                    mAppointmentNames.add(a.getPatient().getName() + " at "+ a.getDate());
                }
                initRecyclerView();
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
        RecyclerView recyclerView = findViewById(R.id.pastAppointments);
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