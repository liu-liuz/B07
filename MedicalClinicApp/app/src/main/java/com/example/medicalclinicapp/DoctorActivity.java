package com.example.medicalclinicapp;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DoctorActivity extends AppCompatActivity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_activity);
        User this_user = (User)getIntent().getSerializableExtra("this_user");

//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
//        User user = new User("user1","12345","Doctor");
//        ref.child("users").child("u1").setValue(user);


        //links to page to view upcoming appointments
        configureUpcomingAppointmentsButton();
        configurePastAppointmentsButton();

    }

    //button to go to 'Upcoming Appointments'
    private void configureUpcomingAppointmentsButton(){
        Button nextButton = (Button) findViewById(R.id.doctorUpcomingAppointments);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorActivity.this, DoctorAppointments.class));
            }
        });
    }

    //button to go to 'Past Appointments'
    private void configurePastAppointmentsButton(){
        Button nextButton = (Button) findViewById(R.id.doctorPastAppointments);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorActivity.this, DoctorPastAppointments.class));
                //change the DoctorAppointments.class to the new activity name
            }
        });
    }


}
