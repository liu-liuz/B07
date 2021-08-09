package com.example.medicalclinicapp;

import android.content.DialogInterface;
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
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class Book extends AppCompatActivity {
    CalendarView calendarView;
    TextView date;
    String[] time = new String[]{"9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00",
            "17:00", "18:00", "19:00", "20:00"};

    Button RadioButton;
    SimpleDateFormat format;
    ListView timeListView;
    String dateChosen;
    RadioOnClick radioOnClick = new RadioOnClick(1);
    Patient pat;
    Doctor doc;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);

        pat = (Patient)getIntent().getSerializableExtra("patient");
        doc = (Doctor)getIntent().getSerializableExtra("doctor");

        calendarView = (CalendarView) findViewById(R.id.calendar);
        date = (TextView) findViewById(R.id.date);
        format = new SimpleDateFormat("yyyy/MM/dd  HH:mm");
        dateChosen = format.format(new Date());;
        date.setText(dateChosen);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String d = i + "/"+ (i1 + 1) + "/" + i2;
                dateChosen = d;
                date.setText(d);
            }
        });

        RadioButton = (Button) findViewById(R.id.choose_time);
        RadioButton.setOnClickListener(new RadioClickListener());

    }
    class RadioClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            AlertDialog dialog =new AlertDialog.Builder(Book.this).setTitle("Choose your time")
                    .setSingleChoiceItems(time,radioOnClick.getIndex(),radioOnClick).create();
            timeListView = dialog.getListView();
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
            dialog.dismiss();
            dateChosen += "  "+time[index];
            date.setText(dateChosen);
        }
    }
    public void book(View view) throws ParseException {
        Date input = format.parse(dateChosen);
        String id = UUID.randomUUID().toString();
        Appointment app = new Appointment(id,input,doc,pat);
        //Add to upcoming...
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("appointments").child(id).setValue(app);
    }
}
