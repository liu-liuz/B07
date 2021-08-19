package com.example.medicalclinicapp;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainModel {
    private static MainPresenter mainPresenter;
    public MainModel(){}
//        input in firebase for testing
//        Doctor d1 = new Doctor("Sarah","Female","Psychiatry");
//        Doctor d2 = new Doctor("Dave","Male","Cardiologist");
//        Doctor d3 = new Doctor("Harry","Male","Cardiologist");
//
//        d1.addWeeklyAvailables(weekAvailable);
//        d2.addWeeklyAvailables(weekAvailable);
//        d3.addWeeklyAvailables(weekAvailable);
//
//
//        User u1 = new User("u1","12345","Doctor",d1);
//        User u3 = new User("u3","12345","Doctor",d2);
//        User u4 = new User("u4","12345","Doctor",d3);
//
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
//        ref.child("users").child("u1").setValue(u1);
//        ref.child("users").child("u3").setValue(u3);
//        ref.child("users").child("cd8e1d67-3e31-44b0-b7b5-d12dd38997e3").setValue(u4);

    public void updateDoc(MainModel m, MainActivity a) {
        mainPresenter = new MainPresenter(m,a);
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd  HH:mm");
        //format.setTimeZone(TimeZone.getTimeZone("EDT"));
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");
        ValueEventListener listener = new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Date today = new Date();
                for(DataSnapshot child:dataSnapshot.getChildren()) {
                    User user = child.getValue(User.class);
                    if (user.getType().equals("Doctor")) {
                        String key = child.getKey();
                        Doctor doctor = user.getDoctorAccount();
                        //uncomment this when first time running and leave it commented after that.
                        //doctor.getWeekly_availabilities().clear();
                        Date first = null;
                        try {
                            if(doctor.getWeekly_availabilities().isEmpty()){
                                doctor.addWeeklyAvailables(mainPresenter.adjustAvailable(7));
                            }
                            first = format.parse(doctor.getWeekly_availabilities().get(0));
                            if (first.getDate() < today.getDate()) {//old date in database;
                                for (int i = 0; i < doctor.getWeekly_availabilities().size(); i++) {
                                    Date temp = format.parse(doctor.getWeekly_availabilities().get(i));
                                    if(temp.getDate() < today.getDate()){
                                        doctor.removeWeeklyAvailable(i);
                                    }
                                }
                            }
                            else if(first.getDay() == today.getDay() && first.before(today)) {
                                for (int i = 0; i < doctor.getWeekly_availabilities().size(); i++) {
                                    Date temp = format.parse(doctor.getWeekly_availabilities().get(i));
                                    if(temp.getHours()>today.getHours()){
                                        break;
                                    }
                                    doctor.removeWeeklyAvailable(i);
                                }
                            }
                            user.setDoctorAccount(doctor);
                            ref.child(key).setValue(user);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                Log.w("info", "Failed to read value.", databaseError.toException());
            }
        };
        ref.addValueEventListener(listener);
    }

    public static void checkLogin(String username, String password, String type, MainModel m, MainActivity a) {
        mainPresenter = new MainPresenter(m,a);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child:dataSnapshot.getChildren()) {
                    User u = child.getValue(User.class);
                    if (u.getUsername().toString().equals(username) && u.getPassword().toString().equals(password) && u.getType().toString().equals(type)) {
                        mainPresenter.login(u,child.getKey());
                        break;
                    }
                }
                mainPresenter.login(null,null);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException();
            }
        };
        ref.addValueEventListener(listener);
    }
}
