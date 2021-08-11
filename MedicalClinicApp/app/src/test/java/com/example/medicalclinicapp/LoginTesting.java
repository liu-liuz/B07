package com.example.medicalclinicapp;

import android.widget.Button;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


//JUnit Test Suite for the Presenter class:

@RunWith(MockitoJUnitRunner.class)
public class LoginTesting{

    @Mock
    MainActivity view;

    //@Mock
    //MainActivity model;
    //Model model;
    //Model class not yet created; just using MainActivity for now to test methods


    /*
    //TEST PRESENTER

    //successful login - putExtra, startActivity are called
    @Test
    public void successfulLogin(){
    }


    //unsuccessful login (wrong password)  - finish is called, then startActivity is called
    @Test
    public void unsuccessfulLoginPassword(){
    }

    //unsuccessful login (wrong username)
    @Test
    public void unsuccessfulLoginUsername(){
    }


    //unsuccessful login (in wrong section:  patient logging in as a doctor)
    @Test
    public void unsuccessfulLoginPatientAsDoctor(){
    }

    //unsuccessful login (in wrong section:  doctor logging in as a patient)
    @Test
    public void unsuccessfulLoginDoctorAsPatient(){
    }
    */


}

