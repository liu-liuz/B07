package com.example.medicalclinicapp;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter"; //for debugging
    private ArrayList<String> mAppointmentNames = new ArrayList<>();

    //constructor for the adapter
    public RecyclerViewAdapter(ArrayList<String> appointmentNames){
        mAppointmentNames = appointmentNames;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflates the view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");
        //the log will print this n times, where n is the number of items in the list
        holder.appointmentName.setText(mAppointmentNames.get(position));

    }

    @Override
    public int getItemCount() {
        //make sure this is not 0, otherwise nothing will show up
        return mAppointmentNames.size();
    }

    //ViewHolder class
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView appointmentName;
        ConstraintLayout parent_layout;

        //constructor for viewHolder
        public ViewHolder(View itemView) {
            super(itemView);
            appointmentName = itemView.findViewById(R.id.appointment);
            parent_layout = itemView.findViewById(R.id.parentLayout);
        }
    }
}
