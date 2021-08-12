package com.example.medicalclinicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainPresenter = new MainPresenter(new MainModel(),this);
        mainPresenter.update();
    }

    public void onRadioButtonClicked(View view) {
        RadioButton patientButton = (RadioButton) findViewById(R.id.Patient);
        ImageView img = (ImageView)findViewById(R.id.patient_doctor);

        if (patientButton.isChecked()) {
            TextView text = (TextView) findViewById(R.id.text_type);
            text.setText("Patient");
            img.setImageResource(R.drawable.patient_login);
        } else {
            TextView text1 = (TextView) findViewById(R.id.text_type);
            text1.setText("Doctor");
            img.setImageResource(R.drawable.doctor_login);
        }
    }

    public String getUsername(){
        String username = ((EditText)findViewById(R.id.username)).getText().toString();
        return username;
    }

    public String getType(){
        String type = ((TextView)findViewById(R.id.text_type)).getText().toString();
        return type;
    }

    public String getPassword(){
        String password = ((EditText)findViewById(R.id.password)).getText().toString();
        return password;
    }

    public void loginSuccess(User user, String key){
        //mainPresenter.update();
        if(getType().equals("Doctor")) {
            Intent i = new Intent(MainActivity.this, DoctorActivity.class);
            i.putExtra("this_user", user);
            startActivity(i);
        }
        else{
            Intent i = new Intent(MainActivity.this,PatientActivity.class);
            i.putExtra("this_user", user);
            i.putExtra("pKey",key);
            startActivity(i);
        }
    }

    public void loginFail(){
        finish();
        startActivity(getIntent());
    }

    public void login(View view){
        Button login = findViewById(R.id.login);
        login.setBackground(getResources().getDrawable(R.drawable.clicked_roundbutton));
        mainPresenter.checkLogin();
    }

    public void register(View view) {
        Button register = findViewById(R.id.register);
        register.setTextColor(getResources().getColor(R.color.light_blue));
        Intent i = new Intent(MainActivity.this,RegisterActivity.class);
        i.putExtra("weekAvailable",mainPresenter.adjustAvailable(7));
        startActivity(i);
    }
}
