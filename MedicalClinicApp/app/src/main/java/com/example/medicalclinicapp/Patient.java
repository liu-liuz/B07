package com.example.medicalclinicapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Patient implements Serializable {
    //each patient will have a name, a gender, date of birth, a list of previous appointments, a list of upcoming appointments, and a list of doctors at the clinic that they have previously seen.
    private String name;
    private String gender;
    private String birthdate;
    //list of appointment id
    private List<String> previous = new ArrayList<String>();
    private List<String> upcoming = new ArrayList<String>();
    private List<String> visited = new ArrayList<String>();

    //constructor
    public Patient(){ }
    public Patient(String name, String gender, String birthdate){
        this.name = name;
        this.gender = gender;
        this.birthdate = birthdate;
    }

    //Just for testing
    public void addToPrevious(Appointment app){
        previous.add(app.getId());
        visited.add(app.getDoctor().getName());
        app.getDoctor().getVisited().add(app.getPatient().getName());
    }


    //getter and setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public List<String> getPrevious() {
        return previous;
    }

    public void setPrevious(ArrayList<String> previous) {
        this.previous = previous;
    }

    public List<String> getUpcoming() {
        return upcoming;
    }

    public void setUpcoming(ArrayList<String> upcoming) {
        this.upcoming = upcoming;
    }

    public List<String> getVisited() {
        return visited;
    }

    public void setVisited(ArrayList<String> visited) {
        this.visited = visited;
    }
}
