package com.example.medicalclinicapp;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password;
    private String type;
    private Patient PatientAccount;
    private Doctor DoctorAccount;

    //constructor
    public User(){}
    public  User(String username, String password, String type, Doctor account){
        this.password = password;
        this.username = username;
        this.type = type;
        this.DoctorAccount = account;
        this.PatientAccount = null;
    }

    public  User(String username, String password, String type, Patient account){
        this.password = password;
        this.username = username;
        this.type = type;
        this.PatientAccount = account;
        this.DoctorAccount = null;
    }

    //getter and setter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Patient getPatientAccount() {
        return PatientAccount;
    }

    public void setPatientAccount(Patient patientAccount) {
        PatientAccount = patientAccount;
    }

    public Doctor getDoctorAccount() {
        return DoctorAccount;
    }

    public void setDoctorAccount(Doctor doctorAccount) {
        DoctorAccount = doctorAccount;
    }

    @Override
    public String toString(){
        return "Username: "+ this.username + ", "+ "Password: " + this.password + ", " + "Type: " + this.type;
    }
}
