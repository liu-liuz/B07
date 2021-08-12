package com.example.medicalclinicapp;

import com.google.firebase.database.DataSnapshot;

public interface MyCallback {
    void onCallback(User value);
    void onCallback(String value);
}
