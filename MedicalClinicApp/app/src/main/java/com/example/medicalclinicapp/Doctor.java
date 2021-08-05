package com.example.medicalclinicapp;

import java.util.ArrayList;
import java.util.List;

public class Doctor{
    //each doctor has a name, a gender, weekly availabilities, specializations, and a list of patients who have visited them so far
    private String name;
    private String gender;
    private List<String> weekly_availabilities = new ArrayList<String>();
    private String specialization;
    private List<String> visited = new ArrayList<String>();

    //constructor
    public Doctor(){}
    public Doctor(String name, String gender, String specialization){
        this.name = name;
        this.gender = gender;
        this.specialization = specialization;
    }

    //for testing
    public void addWeeklyAvailable(String date){
        weekly_availabilities.add(date);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", specialization='" + specialization +
                '}';
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

    public List<String> getWeekly_availabilities() {
        return weekly_availabilities;
    }

    public void setWeekly_availabilities(List<String> weekly_availabilities) {
        this.weekly_availabilities = weekly_availabilities;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public List<String> getVisited() {
        return visited;
    }

    public void setVisited(ArrayList<String> visited){
        this.visited = visited;
    }
}
