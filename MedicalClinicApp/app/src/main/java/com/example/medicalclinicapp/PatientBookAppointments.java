package com.example.medicalclinicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.cert.PolicyNode;
import java.util.ArrayList;
import java.util.List;

public class PatientBookAppointments extends AppCompatActivity {
    User this_user;
    String pKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_book_appointments);

        ArrayList<Doctor> doctorList = new ArrayList<Doctor>();
        ArrayList<String> keys = new ArrayList<String>();
        ArrayList<User> users = new ArrayList<User>();

        DoctorAdp adapter = new DoctorAdp(this, doctorList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        EditText inputSearch = (EditText) findViewById(R.id.myFilter);
        listView.setAdapter(adapter);

        FirebaseDatabase.getInstance().getReference().child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    User user = child.getValue(User.class);
                    if (user.getType().equals("Doctor")) {
                        doctorList.add(user.getDoctorAccount());
                        keys.add(child.getKey());
                        users.add(user);
                    }
                    ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("info", "Failed to read value.", error.toException());
            }
        });
        configureBackButton();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Doctor doc = doctorList.get(position);
                String key = keys.get(position);
                User d_user = users.get(position);
                this_user = (User) getIntent().getSerializableExtra("this_user");
                pKey = (String) getIntent().getSerializableExtra("pKey");
                Intent i = new Intent(PatientBookAppointments.this, Book.class);
                i.putExtra("key", key);
                i.putExtra("pKey", pKey);
                i.putExtra("dUser", d_user);
                i.putExtra("user", this_user);
                i.putExtra("doctor", doc);
                i.putExtra("patient", this_user.getPatientAccount());
                startActivity(i);
            }
        });

        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) { }

            @Override
            public void afterTextChanged(Editable arg0) {}
        });
}
    private void configureBackButton() {
        Button backButton = (Button) findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}