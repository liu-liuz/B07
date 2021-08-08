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
        //System.out.println(this_user.getUsername());
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
                Intent intent = new Intent(DoctorActivity.this, DoctorAppointments.class);
                intent.putExtra("this_user", this_user);
                startActivity(intent);
                //change the DoctorAppointments.class to the new activity name
            }
        });
    }


}
