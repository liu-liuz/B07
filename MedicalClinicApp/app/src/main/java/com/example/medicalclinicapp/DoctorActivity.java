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

//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
//        User user = new User("user1","12345","Doctor");
//        ref.child("users").child("u1").setValue(user);


        //links to page to view upcoming appointments
        configureNextButton();

    }

    private void configureNextButton(){
        Button nextButton = (Button) findViewById(R.id.doctorUpcomingAppointments);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorActivity.this, DoctorAppointments.class));
            }
        });
    }
}
