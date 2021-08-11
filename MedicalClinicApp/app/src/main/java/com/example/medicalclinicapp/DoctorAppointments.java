package com.example.medicalclinicapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

public class DoctorAppointments extends AppCompatActivity {
    private static final String TAG = "DoctorAppointment";       //added this line for debugging
    private ArrayList<Appointment> mAppointment  = new ArrayList<>();
    private ArrayList<String> mAppointmentNames  = new ArrayList<>();   //added variables - the same one that we had in the adapter
    private ArrayList<String> mPatientInfo = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_appointments);
        Log.d(TAG, "onCreate: started.");

        User this_user = (User)getIntent().getSerializableExtra("this_user");
        FirebaseDatabase.getInstance().getReference().child("appointments").addListenerForSingleValueEvent(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Appointment appt = snapshot.getValue(Appointment.class);
                    Date currentDate = Calendar.getInstance().getTime();
                    if (appt.getDoctor().getName().equals(this_user.getDoctorAccount().getName())){
                        if ((appt.getDate().compareTo(currentDate) > 0) || (appt.getDate().compareTo(currentDate) == 0)){
                            mAppointment.add(appt);

                        }
                    }
                }
                mAppointment.sort(Comparator.comparing(Appointment::getDate));
                for(Appointment a: mAppointment){
                    mAppointmentNames.add(a.getPatient().getName() + " at "+ a.getDate());
                    mPatientInfo.add("\n\tPATIENT INFORMATION \n-----------------------------------------------------\n\tNAME: " + a.getPatient().getName() + "\n\tGENDER: " + a.getPatient().getGender()  + "\n\tDATE OF BIRTH: " + a.getPatient().getBirthdate() + "\n\n\tPATIENT HISTORY\n-----------------------------------------------------\n\tDOCTORS SEEN:" + a.getPatient().getVisited() + "\n");
                }
                initRecyclerView();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadAppointment:onCancelled", databaseError.toException());
            }
        });

        configureBackButton();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview");
        RecyclerView recyclerView = findViewById(R.id.upcomingAppointments);         //upcomingAppointments is from activity_doctor_appointments.xml

        ClickableRecyclerViewAdapter adapter = new ClickableRecyclerViewAdapter(this, mAppointmentNames, mPatientInfo);    //ADDED PARAMETER
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