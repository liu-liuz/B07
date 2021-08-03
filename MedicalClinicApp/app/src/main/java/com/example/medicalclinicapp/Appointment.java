package com.example.medicalclinicapp;

import java.util.Date;

public class Appointment {
    private String id;
    private String date;
    private Doctor doctor;
    private Patient patient;

    //constructor
    public Appointment(){}
    public Appointment(String id, String date, Doctor doctor, Patient patient){
        this.date = date;
        this.id = id;
        this.doctor = doctor;
        this.patient = patient;
    }

    //getter and setter

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
