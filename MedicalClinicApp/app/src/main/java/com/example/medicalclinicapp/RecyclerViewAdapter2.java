package com.example.medicalclinicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapter2 extends RecyclerView.Adapter<RecyclerViewAdapter2.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter"; //for debugging
    private ArrayList<String> mDoctorNames = new ArrayList<>();

    //constructor for the adapter
    public RecyclerViewAdapter2(ArrayList<String> DoctorNames){
        mDoctorNames = DoctorNames;
    }


    @Override
    public RecyclerViewAdapter2.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflates the view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_row, parent, false);
        return new RecyclerViewAdapter2.ViewHolder(view);

    }


    @Override
    public void onBindViewHolder(RecyclerViewAdapter2.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");
        //the log will print this n times, where n is the number of items in the list
        holder.DoctorName.setText(mDoctorNames.get(position));

    }

    @Override
    public int getItemCount() {
        //make sure this is not 0, otherwise nothing will show up
        return mDoctorNames.size();
    }

    //ViewHolder class
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView DoctorName;
        ConstraintLayout parent_layout;

        //constructor for viewHolder
        public ViewHolder(View itemView) {
            super(itemView);
            DoctorName = itemView.findViewById(R.id.doctorName);
            parent_layout = itemView.findViewById(R.id.parentLayout);
        }
    }
}