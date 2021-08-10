package com.example.medicalclinicapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class PatientAppointments extends AppCompatActivity {

    private static final String TAG = "PatientAppointment";       //added this line for debugging
    private ArrayList<Appointment> mAppointment  = new ArrayList<>();
    private ArrayList<String> mAppointmentNames  = new ArrayList<>();   //added variables - the same one that we had in the adapter
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_appointments);




        Log.d(TAG, "onCreate: started.");
        //populating the array with strings for testing
        //do this from the database after
        User this_user = (User)getIntent().getSerializableExtra("this_user");
        FirebaseDatabase.getInstance().getReference().child("appointments").addListenerForSingleValueEvent(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    Appointment appt = snapshot.getValue(Appointment.class);
                    Date currentDate = Calendar.getInstance().getTime();
                    if (appt.getPatient().getName().equals(this_user.getPatientAccount().getName())) {
                        //Currently just checking if doctor name matches
                        //Should check if it matches current doctor name and time is not already complete
                        if ((appt.getDate().compareTo(currentDate) > 0) || (appt.getDate().compareTo(currentDate) == 0)) {
                            mAppointment.add(appt);
                        }
                    }
                }
                mAppointment.sort(Comparator.comparing(Appointment::getDate));
                for(Appointment a: mAppointment) {
                    mAppointmentNames.add("Patient Name: " + a.getPatient().getName() + " \nAt: " + a.getDate() + "\nAppointment ID: " + a.getId() + "\nDoctor: " + a.getDoctor().getName()
                            + "\nDoctor Specialization: " + a.getDoctor().getSpecialization());
                }

                initRecyclerView();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadAppointment:onCancelled", databaseError.toException());
            }
        });
        configureBackButton();

        //mAppointmentNames.add("Appointment ID: 23455 \nPatient Name:  \nDoctorName: \nAppointmentDate:");
        //mAppointmentNames.add("Appointment ID: 45372 \nPatient Name:  \nDoctorName: \nAppointmentDate:");
        //mAppointmentNames.add("Appointment ID: 89765 \nPatient Name:  \nDoctorName: \nAppointmentDate:");
        //mAppointmentNames.add("Appointment ID: 45376 \nPatient Name:  \nDoctorName: \nAppointmentDate:");
        //mAppointmentNames.add("Appointment ID: 38458 \nPatient Name:  \nDoctorName: \nAppointmentDate:");
        //mAppointmentNames.add("Appointment ID: 37654 \nPatient Name:  \nDoctorName: \nAppointmentDate:");
        //mAppointmentNames.add("Appointment ID: 37546 \nPatient Name:  \nDoctorName: \nAppointmentDate:");
        //mAppointmentNames.add("Appointment ID: 37463 \nPatient Name:  \nDoctorName: \nAppointmentDate:");
        //mAppointmentNames.add("Appointment ID: 23746 \nPatient Name:  \nDoctorName: \nAppointmentDate:");
        //mAppointmentNames.add("Appointment ID: 32436 \nPatient Name:  \nDoctorName: \nAppointmentDate:");
        //initiating the recycler view
        //initRecyclerView();

        //configureBackButton();

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