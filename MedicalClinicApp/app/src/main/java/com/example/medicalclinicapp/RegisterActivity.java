package com.example.medicalclinicapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;

public class RegisterActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
    }

    public void onRadioButtonClicked(View view) {
        RadioButton patientButton = (RadioButton) findViewById(R.id.Patient);
        EditText birthday = (EditText) findViewById(R.id.birthday);
        EditText specialization = (EditText) findViewById(R.id.specialization);

        if (patientButton.isChecked()) {
            TextView text = (TextView) findViewById(R.id.text_type);
            text.setText("Patient");
            specialization.setVisibility(View.GONE);
            birthday.setVisibility(View.VISIBLE);

        } else {
            TextView text1 = (TextView) findViewById(R.id.text_type);
            text1.setText("Doctor");
            birthday.setVisibility(View.GONE);
            specialization.setVisibility(View.VISIBLE);
        }
    }

    public void signIn(View view) {
        Button sign_in = findViewById(R.id.sign_in);
        sign_in.setTextColor(getResources().getColor(R.color.light_blue));
        Intent i = new Intent(RegisterActivity.this,MainActivity.class);
        startActivity(i);
    }

    final Calendar myCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

        private void updateLabel() {
            TextView birthday = (TextView) findViewById(R.id.birthday);
            String myFormat = "MMMM dd, yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

            birthday.setText(sdf.format(myCalendar.getTime()));
        }

    };

    public void datePicker(View view){
        new DatePickerDialog(this, R.style.DialogTheme, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void register(View view){
        Button sign_up = findViewById(R.id.sign_up);
        sign_up.setBackground(getResources().getDrawable(R.drawable.clicked_roundbutton));
        RadioButton patientButton = (RadioButton) findViewById(R.id.Patient);
        String username = ((EditText)findViewById(R.id.username)).getText().toString();
        String password = ((EditText)findViewById(R.id.password)).getText().toString();
        String name = ((EditText)findViewById(R.id.name)).getText().toString();
        String gender = ((EditText)findViewById(R.id.gender)).getText().toString();
        if(patientButton.isChecked()){
            String birthday = ((EditText)findViewById(R.id.birthday)).getText().toString();
            Patient pat = new Patient(name,gender,birthday);
            User user = new User(username,password,"Patient",pat);
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
            ref.child("users").child(UUID.randomUUID().toString()).setValue(user);
            //jump to patient page and pass data (left to implement)
        }
        else{
            String specialization = ((EditText)findViewById(R.id.specialization)).getText().toString();
            Doctor doc = new Doctor(name,gender,specialization);
            User user = new User(username,password,"Doctor",doc);
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
            ref.child("users").child(UUID.randomUUID().toString()).setValue(user);
            Intent intent = new Intent(RegisterActivity.this, DoctorActivity.class);
            //passing data to next activity
            //System.out.println(user.getUsername());
            intent.putExtra("this_user", user);
            startActivity(intent);
        }
    }
}

