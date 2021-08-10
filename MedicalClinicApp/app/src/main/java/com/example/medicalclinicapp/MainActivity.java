package com.example.medicalclinicapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

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

public class MainActivity extends AppCompatActivity {
    ArrayList<String> weekAvailable = new ArrayList<String>();
    ArrayList<String> dayAvailable = new ArrayList<String>();
    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd  HH:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adjustAvailable();

        // setup basic database
//        Doctor doc = new Doctor("Sarah","F","Psychiatry");
//        Doctor doc1 = new Doctor("Dave","M","Cardiologist");
//        Doctor doc2 = new Doctor("Harry","M","Cardiologist");
//        Patient pat = new Patient("Susan","F","March 1,2012");
//        doc.addWeeklyAvailables(weekAvailable);
//        doc1.addWeeklyAvailables(weekAvailable);
//        doc2.addWeeklyAvailables(weekAvailable);


//          Appointment app = new Appointment("1895",new Date(2020 , 6 , 28 ), doc, pat);

//        User user = new User("user1","12345","Doctor",doc);
//        User user1 = new User("user3","12345","Doctor",doc1);
//        User user2 = new User("user4","12345","Doctor",doc2);
//        User user3 = new User("user2","12345","Patient",pat);


//        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference();
//        ref1.child("users").child("u3").setValue(user1);
//        ref1.child("users").child("u1").setValue(user);
//        ref1.child("users").child("cd8e1d67-3e31-44b0-b7b5-d12dd38997e3").setValue(user2);
//        ref1.child("users").child("u2").setValue(user3);
//        ref1.child("appointments").child(app.getId()).setValue(app);
        updateDoc();
    }

    public void updateDoc() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");
        ValueEventListener listener = new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Date today = new Date();
                for(DataSnapshot child:dataSnapshot.getChildren()) {
                    User user = child.getValue(User.class);
                    if (user.getType().equals("Doctor")) {
                        String key = child.getKey();
                        Doctor doctor = user.getDoctorAccount();
                        Date first = null;
                        try {
                            first = format.parse(doctor.getWeekly_availabilities().get(0));
                            if (first.getDay() < today.getDay()) {
                                doctor.addWeeklyAvailables(dayAvailable);
                                for (int i = 0; i < 12; i++) {
                                    doctor.removeWeeklyAvailable(i);
                                }
                            } else { //Remove passed time
                                for (int i = 0; i < 12; i++) {
                                    try {
                                        Date temp = format.parse(doctor.getWeekly_availabilities().get(i));
                                        if (temp.before(new Date())) {
                                            doctor.removeWeeklyAvailable(i);
                                        }
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            user.setDoctorAccount(doctor);
                            ref.child(key).setValue(user);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                Log.w("info", "Failed to read value.", databaseError.toException());
            }
        };
        ref.addValueEventListener(listener);
    }

    public void adjustAvailable(){
        Date today = new Date();
        // add week available
        for(int i = 0; i < 7; i++){
            if(today.getHours()<21){
                for (int j = 0; j<12; j++){
                    weekAvailable.add(format.format(new Date(today.getYear(),today.getMonth(),(today.getDate()+i),9+j,0,0)));
                }
            }
            else{
                for (int j = 0; j<12; j++){
                    weekAvailable.add(format.format(new Date(today.getYear(),today.getMonth(),(today.getDate()+1+i),9+j,0,0)));
                }
            }
        }

        // add day available
        for (int j = 0; j<12; j++){
            dayAvailable.add(format.format(new Date(today.getYear(),today.getMonth(),(today.getDay()+1),9+j,0,0)));
        }
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

    public void login(View view){
        Button login = findViewById(R.id.login);
        login.setBackground(getResources().getDrawable(R.drawable.clicked_roundbutton));
        // Read from the database
        String username = ((EditText)findViewById(R.id.username)).getText().toString();
        String password = ((EditText)findViewById(R.id.password)).getText().toString();
        String type = ((TextView)findViewById(R.id.text_type)).getText().toString();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");
        ValueEventListener listener = new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child:dataSnapshot.getChildren()){
                    User user = child.getValue(User.class);
                    if(user.getUsername().toString().equals(username) && user.getPassword().toString().equals(password)
                            && user.getType().equals(type)){
                        //login succeed
                        if(user.getType().equals("Doctor")) {
                            Intent i = new Intent(MainActivity.this, DoctorActivity.class);
                            i.putExtra("this_user", user);
                            startActivity(i);
                        }
                        else{
                            String pKey = child.getKey();
                            Intent i = new Intent(MainActivity.this,PatientActivity.class);
                            i.putExtra("this_user", user);
                            i.putExtra("pKey",pKey);
                            startActivity(i);
                        }
                    }
                }
                //login fail
                finish();
                startActivity(getIntent());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                Log.w("info", "Failed to read value.", databaseError.toException());
            }
        };
        ref.addValueEventListener(listener);
    }

    public void register(View view) {
        Button register = findViewById(R.id.register);
        register.setTextColor(getResources().getColor(R.color.light_blue));
        Intent i = new Intent(MainActivity.this,RegisterActivity.class);
        i.putExtra("weekAvailable",weekAvailable);
        startActivity(i);
    }
}