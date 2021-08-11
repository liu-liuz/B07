package com.example.medicalclinicapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.view.View.OnClickListener;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ClickableRecyclerViewAdapter extends RecyclerView.Adapter<ClickableRecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "ClickableRecyclerViewAdapter";
    private ArrayList<String> mAppointmentNames = new ArrayList<>();
    private ArrayList<String> mPatientInfo = new ArrayList<>();
    private Context mContext;

    //should add context
    public ClickableRecyclerViewAdapter(Context context, ArrayList<String> appointmentNames,  ArrayList<String> patientInfo){
        mAppointmentNames = appointmentNames;
        mPatientInfo = patientInfo;
        mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ClickableRecyclerViewAdapter.ViewHolder holder, int position) {
        Log.d("TAG", "onBindViewHolder: called");
        holder.appointmentName.setText(mAppointmentNames.get(position));


        //CHANGED FROM PARENT_LAYOUT TO appointmentName
        holder.appointmentName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "onClick: clicked on an item");
                Intent intent = new Intent(mContext, PatientProfile.class);
                intent.putExtra("appointment_info", mPatientInfo.get(position));
                Log.i("TAG", "INTENT CREATED AND THE FOLLOWING INFORMATION HAS BEEN SENT" + mPatientInfo.get(position));
                mContext.startActivity(intent);
            }
        });
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
            //appointment, parentLayout are IDs in recyclerview_row.xml
        }

    }
}
