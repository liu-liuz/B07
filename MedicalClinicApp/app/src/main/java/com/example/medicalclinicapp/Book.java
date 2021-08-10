package com.example.medicalclinicapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class Book extends AppCompatActivity {
    CalendarView calendarView;
    TextView date;
    ListView timeListView;
    Button RadioButton;
    RadioOnClick radioOnClick = new RadioOnClick(1);

    String[] timeArray = new String[12];
    ArrayList<String> timeList = new ArrayList<String>();


    SimpleDateFormat format, format2;
    String dateChosen, key, pKey;
    Patient pat;
    Doctor doc;
    User user, d_user;
    Date input;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);

        pat = (Patient)getIntent().getSerializableExtra("patient");
        doc = (Doctor)getIntent().getSerializableExtra("doctor");
        key = (String)getIntent().getSerializableExtra("key");
        pKey = (String) getIntent().getSerializableExtra("pKey");
        user = (User)getIntent().getSerializableExtra("user");
        d_user = (User)getIntent().getSerializableExtra("dUser");

        calendarView = (CalendarView) findViewById(R.id.calendar);
        date = (TextView) findViewById(R.id.date);
        format = new SimpleDateFormat("yyyy/MM/dd  HH:mm");
        format2 = new SimpleDateFormat("yyyy/MM/dd");
        Date today = new Date();
        dateChosen = format2.format(today);
        //day of next week
        long week = System.currentTimeMillis() + 6 * 24 * 3600 * 1000;
        Date next = new Date(week);
        //set max and min for calendar
        calendarView.setMinDate(today.getTime());
        calendarView.setMaxDate(next.getTime());
        input = today;


        try {
            addTimeChoice();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        date.setText(dateChosen);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String d = i + "/"+ (i1 + 1) + "/" + i2;
                dateChosen = d;
                try {
                    input = format2.parse(d);
                    addTimeChoice();
                    date.setText(d);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        RadioButton = (Button) findViewById(R.id.choose_time);
        RadioButton.setOnClickListener(new RadioClickListener());

    }
    public void addTimeChoice() throws ParseException {
        timeList.removeAll(timeList);
        timeArray = new String[timeList.size()];
        for(int i = 0; i < doc.getWeekly_availabilities().size(); i++){
            Date temp = format.parse(doc.getWeekly_availabilities().get(i));
            if(input.getDate() == temp.getDate()){
                timeList.add(doc.getWeekly_availabilities().get(i));
            }
        }
        timeArray = timeList.toArray(timeArray);
    }

    class RadioClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            AlertDialog dialog = new AlertDialog.Builder(Book.this).setTitle("Choose your time")
                    .setSingleChoiceItems(timeArray,radioOnClick.getIndex(),radioOnClick).create();
            timeListView = dialog.getListView();
            dialog.setCanceledOnTouchOutside(true);
            dialog.show();
        }
    }

    class RadioOnClick implements DialogInterface.OnClickListener{
        private int index;

        public RadioOnClick(int index){
            this.index = index;
        }
        public void setIndex(int index){
            this.index=index;
        }
        public int getIndex(){
            return index;
        }

        public void onClick(DialogInterface dialog, int whichButton){
            setIndex(whichButton);
            dateChosen = timeArray[index];
            date.setText(dateChosen);
            dialog.dismiss();
        }
    }

    public void book(View view) throws ParseException {
        //Update user
        try {
            input = format.parse(dateChosen);
            String id = UUID.randomUUID().toString();

            d_user.getDoctorAccount().removeWeeklyAvailable(dateChosen);
            user.getPatientAccount().addToUpcoming(id);
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
            ref.child("users").child(key).setValue(d_user);
            ref.child("users").child(pKey).setValue(user);

            Appointment app = new Appointment(id,input,doc,pat);

            //Add to upcoming...
            ref.child("appointments").child(id).setValue(app);

            finish();

        } catch (ParseException e) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(Book.this);
            builder1.setTitle("Alert");
            builder1.setMessage("You haven't choose your time yet!");
            builder1.setCancelable(true);
            builder1.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
        }
    }
}
