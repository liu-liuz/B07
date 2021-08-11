package com.example.medicalclinicapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DoctorAdp extends ArrayAdapter<Doctor> implements Filterable {
    private ArrayList<Doctor> originalList;
    private ArrayList<Doctor> docs = new ArrayList<Doctor>();
    public DoctorAdp(Context context, ArrayList<Doctor> doc) {
        super(context, 0, doc);
        this.originalList = doc;
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

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                ArrayList<Doctor> temp = (ArrayList<Doctor>) results.values;
                originalList.clear();
                originalList.addAll(temp);
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                if(docs.isEmpty()){
                    docs.addAll(originalList);
                }
                ArrayList<Doctor> FilteredArrayNames = new ArrayList<Doctor>();
                FilteredArrayNames.clear();

                if(constraint == null || constraint.length() == 0){
                    FilteredArrayNames.addAll(docs);
                }
                else{
                    constraint = constraint.toString().toLowerCase();
                    String[] splited = (constraint.toString()).split("\\s+");
                    for (int i = 0; i < docs.size(); i++) {
                        Doctor doc = docs.get(i);
                        Boolean contain = true;
                        String docString = doc.getName() + doc.getGender() + doc.getSpecialization();
                        for(int j = 0; j<splited.length; j++){
                            if (!docString.toLowerCase().contains(splited[j])){
                                contain = false;
                                break;
                            }
                        }
                        if(contain){
                            FilteredArrayNames.add(doc);
                        }
                    }
                }

                results.count = FilteredArrayNames.size();
                results.values = FilteredArrayNames;

                return results;
            }
        };
        return filter;
    }
}

