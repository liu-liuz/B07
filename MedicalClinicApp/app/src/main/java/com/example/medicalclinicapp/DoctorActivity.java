package com.example.medicalclinicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
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

public class DoctorActivity extends AppCompatActivity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_activity);
        User this_user = (User)getIntent().getSerializableExtra("this_user");
        TextView name = (TextView)findViewById(R.id.doctor_name);
        TextView gender = (TextView)findViewById(R.id.doctor_gender);
        TextView birthday = (TextView)findViewById(R.id.doctor_specialization);
        name.setText(this_user.getDoctorAccount().getName());
        gender.setText(this_user.getDoctorAccount().getGender());
        birthday.setText(this_user.getDoctorAccount().getSpecialization());
        Log.i("info",this_user.getDoctorAccount().getName().toString());
        //System.out.println(this_user.getUsername());
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
//        User user = new User("user1","12345","Doctor");
//        ref.child("users").child("u1").setValue(user);


        //links to page to view upcoming appointments
        configureUpcomingAppointmentsButton();
        configurePastAppointmentsButton();
        configurePatientInformationButton();
        //configureSignOutButton();

    }

    //button to go to 'Upcoming Appointments'
    private void configureUpcomingAppointmentsButton(){
        Button nextButton = (Button) findViewById(R.id.doctorUpcomingAppointments);
        User this_user = (User)getIntent().getSerializableExtra("this_user");
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoctorActivity.this, DoctorAppointments.class);
                intent.putExtra("this_user", this_user);
                //System.out.println(this_user.getUsername());
                startActivity(intent);
            }
        });
    }

    //button to go to 'Past Appointments'
    private void configurePastAppointmentsButton(){
        Button nextButton = (Button) findViewById(R.id.doctorPastAppointments);
        User this_user = (User)getIntent().getSerializableExtra("this_user");
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoctorActivity.this, DoctorPastAppointments.class);
                intent.putExtra("this_user", this_user);
                startActivity(intent);
                //change the DoctorAppointments.class to the new activity name
            }
        });
    }

    //button to go to 'Patient Information' page
    private void configurePatientInformationButton(){
        Button nextButton = (Button) findViewById(R.id.patientInformation);
        User this_user = (User)getIntent().getSerializableExtra("this_user");
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoctorActivity.this, PatientInformation.class);
                intent.putExtra("this_user", this_user);
                startActivity(intent);
                //change the DoctorAppointments.class to the new activity name
            }
        });
    }



    /*
    //button to 'Sign Out' and go back to home page?
    //problem: once signed out, you can't sign back in
    private void configureSignOutButton(){
        Button nextButton = (Button) findViewById(R.id.signOut);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorActivity.this, MainActivity.class));
                //change the DoctorAppointments.class to the new activity name
            }
        });
    }
    */


}
