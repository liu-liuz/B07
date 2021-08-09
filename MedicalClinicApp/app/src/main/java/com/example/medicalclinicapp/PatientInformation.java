package com.example.medicalclinicapp;

import androidx.appcompat.app.AppCompatActivity;
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
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PatientInformation extends AppCompatActivity {

    private static final String TAG = "DoctorPastAppointment";       //added this line for debugging
    private ArrayList<String> mAppointmentNames  = new ArrayList<>();   //added variables - the same one that we had in the adapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_information);
        User this_user = (User)getIntent().getSerializableExtra("this_user");


        //HAVE TO GET INFORMATION FROM PATIENTS AND ALSO FROM APPOINTMENTS

        Log.d(TAG, "onCreate: started.");
        FirebaseDatabase.getInstance().getReference().child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User patient = snapshot.getValue(User.class);

                    //adds to list iff user is a patient
                    //make sure patient has the specified name
                    if (patient.getType().equals("Patient")) {
                        mAppointmentNames.add("NAME: " + patient.getPatientAccount().getName() + "\nGENDER: " + patient.getPatientAccount().getGender()  + "\nDATE OF BIRTH: " + patient.getPatientAccount().getBirthdate() + "\nPREVIOUS APPOINTMENTS: " + patient.getPatientAccount().getPrevious() + "\nUPCOMING APPOINTMENTS:" + patient.getPatientAccount().getUpcoming() + "\nDOCTORS SEEN:");
                    }
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
        RecyclerView recyclerView = findViewById(R.id.pastAppointments);

        //create RecyclerViewAdapter object + passing in the dataset and the context to the adapter
        Log.d(TAG, "creating RecyclerViewAdapter object");
        //took out 'this' as the last argument
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mAppointmentNames);

        Log.d(TAG, "adapter created");
        recyclerView.setAdapter(adapter);
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