package com.example.medicalclinicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PatientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        //links to page to view upcoming appointments
        configureNextButton();
        configurePastAppointmentsButton();

    }

    private void configurePastAppointmentsButton() {
        Button nextButton = (Button) findViewById(R.id.ViewMyPastAppointments);
        User this_user = (User)getIntent().getSerializableExtra("this_user");
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientActivity.this, PatientPastAppointments.class);
                intent.putExtra("this_user", this_user);
                //System.out.println(this_user.getUsername());
                startActivity(intent);
                //startActivity(new Intent(PatientActivity.this, PatientPastAppointments.class));
                //change the DoctorAppointments.class to the new activity name
            }
        });
    }

    private void configureNextButton() {
        Button nextButton = (Button) findViewById(R.id.ViewMyAppointments);
        Button nextButton1 = (Button) findViewById(R.id.BookMyAppointments);
        User this_user = (User)getIntent().getSerializableExtra("this_user");
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientActivity.this, PatientAppointments.class);
                intent.putExtra("this_user", this_user);
                //System.out.println(this_user.getUsername());
                startActivity(intent);
                //startActivity(new Intent(PatientActivity.this, PatientAppointments.class));
            }
        });

        nextButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PatientActivity.this, PatientBookAppointments.class));
            }
        });
    }
}