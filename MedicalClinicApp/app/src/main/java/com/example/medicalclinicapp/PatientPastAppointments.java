package com.example.medicalclinicapp;

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
import java.util.Comparator;
import java.util.Date;

public class PatientPastAppointments extends AppCompatActivity {

    private static final String TAG = "PatientPastAppointment";       //added this line for debugging
    private ArrayList<Appointment> mAppointment  = new ArrayList<>();
    private ArrayList<String> mAppointmentNames  = new ArrayList<>();   //added variables - the same one that we had in the adapter
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_past_appointments);

        Log.d(TAG, "onCreate: started.");

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
                        if (appt.getDate().compareTo(currentDate) < 0) {
                            mAppointment.add(appt);
                        }
                    }
                }
                mAppointment.sort(Comparator.comparing(Appointment::getDate).reversed());
                for(Appointment a: mAppointment){
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
        //populating the array with strings for testing
        //do this from the database after
        //mAppointmentNames.add("Appointment ID: 23458 \nPatient Name: Jake H. \nDoctorName: Joel Daniel \nAppointmentDate: 4/5/2020");
        //mAppointmentNames.add("Appointment ID: 45376 \nPatient Name: Jake H. \nDoctorName: Joel Daniel \nAppointmentDate: 4/29/2020");
        //mAppointmentNames.add("Appointment ID: 89764 \nPatient Name: Jake H. \nDoctorName: Joel Daniel \nAppointmentDate: 5/19/2020");
        //mAppointmentNames.add("Appointment ID: 45373 \nPatient Name: Jake H. \nDoctorName: Joel Daniel \nAppointmentDate: 6/24/2020");
        //mAppointmentNames.add("Appointment ID: 38459 \nPatient Name: Jake H. \nDoctorName: Joel Daniel \nAppointmentDate: 7/25/2020");
        //mAppointmentNames.add("Appointment ID: 37656 \nPatient Name: Jake H. \nDoctorName: Joel Daniel \nAppointmentDate: 8/9/2020");
        //mAppointmentNames.add("Appointment ID: 37547 \nPatient Name: Jake H. \nDoctorName: Joel Daniel \nAppointmentDate: 8/27/2020");
        //mAppointmentNames.add("Appointment ID: 37460 \nPatient Name: Jake H. \nDoctorName: Joel Daniel \nAppointmentDate: 9/17/2020");
        //mAppointmentNames.add("Appointment ID: 23744 \nPatient Name: Jake H. \nDoctorName: Joel Daniel \nAppointmentDate: 10/2/2020");
        //mAppointmentNames.add("Appointment ID: 32432 \nPatient Name: Jake H. \nDoctorName: Joel Daniel \nAppointmentDate: 10/29/2020");
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
    }
