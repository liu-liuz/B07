package com.example.medicalclinicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PatientActivity extends AppCompatActivity {
    User this_user;
    String pKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        pKey = (String) getIntent().getSerializableExtra("pKey");
        this_user = (User)getIntent().getSerializableExtra("this_user");
        TextView name = (TextView)findViewById(R.id.patient_name);
        TextView gender = (TextView)findViewById(R.id.patient_gender);
        TextView birthday = (TextView)findViewById(R.id.patient_birthday);
        name.setText(this_user.getPatientAccount().getName());
        gender.setText(this_user.getPatientAccount().getGender());
        birthday.setText(this_user.getPatientAccount().getBirthdate());
        Log.i("info",this_user.getPatientAccount().getName().toString());

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
                intent.putExtra("pKey", pKey);
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
                Intent i = new Intent(PatientActivity.this, PatientBookAppointments.class);
                i.putExtra("pKey",pKey);
                i.putExtra("this_user", this_user);
                startActivity(i);
            }
        });
    }
}