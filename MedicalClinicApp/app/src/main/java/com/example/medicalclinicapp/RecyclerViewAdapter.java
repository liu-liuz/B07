package com.example.medicalclinicapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<String> mAppointmentNames = new ArrayList<>();
    //private Context mContext;

    public RecyclerViewAdapter(ArrayList<String> appointmentNames){
        mAppointmentNames = appointmentNames;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");
        holder.appointmentName.setText(mAppointmentNames.get(position));
    }

    @Override
    public int getItemCount() {
        return mAppointmentNames.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView appointmentName;
        ConstraintLayout parent_layout;

        public ViewHolder(View itemView) {
            super(itemView);
            appointmentName = itemView.findViewById(R.id.appointment);
            parent_layout = itemView.findViewById(R.id.parentLayout);
        }
    }
}
