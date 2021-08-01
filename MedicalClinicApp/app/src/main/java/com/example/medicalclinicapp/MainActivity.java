package com.example.medicalclinicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
//        User user = new User("user1","12345","Doctor");
//        ref.child("users").child("u1").setValue(user);
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
                        Log.i("info","succeed");
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
}