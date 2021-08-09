package com.example.medicalclinicapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.medicalclinicapp.Doctor;
import com.example.medicalclinicapp.R;

import java.util.ArrayList;

public class DoctorAdp extends ArrayAdapter<Doctor> {

        public DoctorAdp(Context context, ArrayList<Doctor> doc) {
            super(context, 0, doc);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            Doctor doc = getItem(position);

            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.text_layout, parent, false);
            }

            // Lookup view for data population
            TextView dName = (TextView) convertView.findViewById(R.id.dName);
            TextView dGender = (TextView) convertView.findViewById(R.id.dGender);
            TextView dSpecialization = (TextView) convertView.findViewById(R.id.dSpecialization);

            // Populate the data into the template view using the data object
            dName.setText("Dr. " + doc.getName());
            dGender.setText("Gender: " + doc.getGender());
            dSpecialization.setText("Specialization: " + doc.getSpecialization());

            // Return the completed view to render on screen
            return convertView;
        }
}

