package com.example.medicalclinicapp;

import android.widget.Button;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.firebase.database.DataSnapshot;

import java.util.Calendar;
import java.util.Date;

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

    @Mock
    MainModel model;

    //Model class not yet created; just using MainActivity for now to test methods



    //TEST PRESENTER

    //testing the constructor
    @Test
    public void correctConstructor() {
        MainPresenter presenter1 = new MainPresenter(model, view);
        MainPresenter presenter2 = new MainPresenter(model, view);
        assertEquals(presenter1, presenter2);
    }


    //successful Update
    @Test
    public void successfulUpdate(){
        MainPresenter presenter = new MainPresenter(model, view);
        verify(presenter).mainModel.updateDoc(model, view);
    }

    //successful login - doctor
    @Test
    public void successfulLoginDoctor(){
        MainPresenter presenter = new MainPresenter(model, view);
        Doctor account = new Doctor("Mark", "M", "Neurology");
        User u1 = new User("testUser", "adcde", "Doctor", account);
        when(view.getUsername()).thenReturn("testUser");
        when(view.getPassword()).thenReturn("abcde");
        when(view.getType()).thenReturn("Doctor");
        String key = "abcd123";


        verify(view).loginSuccess(u1,key);
    }

    //successful login - patient
    @Test
    public void successfulLoginPatient(){
        MainPresenter presenter = new MainPresenter(model, view);
        Patient account = new Patient("Jane", "F", "2/5/1992");
        User u1 = new User("testUser1", "123456789", "Patient", account);
        when(view.getUsername()).thenReturn("testUser1");
        when(view.getPassword()).thenReturn("123456789");
        when(view.getType()).thenReturn("Patient");
        String key = "abcd123";

        System.out.println(presenter.key);
        verify(view).loginSuccess(u1,presenter.key);
    }


    //unsuccessful login (wrong password)  - Patient
    @Test
    public void unsuccessfulLoginPasswordPatient(){
        MainPresenter presenter = new MainPresenter(model, view);
        Patient account = new Patient("Jane", "F", "2/5/1992");
        User u1 = new User("testUser1", "123456789", "Patient", account);
        when(view.getUsername()).thenReturn("testUser1");
        when(view.getPassword()).thenReturn("");
        when(view.getType()).thenReturn("Patient");

        verify(view).loginFail();
    }

    //unsuccessful login (wrong username)  - Patient
    @Test
    public void unsuccessfulLoginUsernamePatient(){
        MainPresenter presenter = new MainPresenter(model, view);
        Patient account = new Patient("Jane", "F", "2/5/1992");
        User u1 = new User("testUser1", "123456789", "Patient", account);
        when(view.getUsername()).thenReturn("");
        when(view.getPassword()).thenReturn("123456789");
        when(view.getType()).thenReturn("Patient");

        verify(view).loginFail();
    }

    //unsuccessful login (wrong password)  - Doctor
    @Test
    public void unsuccessfulLoginPasswordDoctor(){
        MainPresenter presenter = new MainPresenter(model, view);
        Doctor account = new Doctor("Mark", "M", "Neurology");
        User u1 = new User("testUser", "adcde", "Doctor", account);
        when(view.getUsername()).thenReturn("testUser");
        when(view.getPassword()).thenReturn("");
        when(view.getType()).thenReturn("Doctor");

        verify(view).loginFail();
    }



    //unsuccessful login (wrong username) - Doctor
    @Test
    public void unsuccessfulLoginUsernameDoctor(){
        MainPresenter presenter = new MainPresenter(model, view);
        Doctor account = new Doctor("Mark", "M", "Neurology");
        User u1 = new User("testUser", "adcde", "Doctor", account);
        when(view.getUsername()).thenReturn("");
        when(view.getPassword()).thenReturn("abcde");
        when(view.getType()).thenReturn("Doctor");

        verify(view).loginFail();
    }


    //unsuccessful login (in wrong section:  doctor with patient account)
    @Test
    public void unsuccessfulLoginPatientAsDoctor(){
        MainPresenter presenter = new MainPresenter(model, view);
        Doctor account = new Doctor("Mark", "M", "Neurology");
        User u1 = new User("testUser", "adcde", "Doctor", account);
        when(view.getUsername()).thenReturn("");
        when(view.getPassword()).thenReturn("abcde");
        when(view.getType()).thenReturn("Patient");

        verify(view).loginFail();
    }

    //unsuccessful login (in wrong section:  patient with doctor account)
    @Test
    public void unsuccessfulLoginDoctorAsPatient(){
        MainPresenter presenter = new MainPresenter(model, view);
        Patient account = new Patient("Jane", "F", "2/5/1992");
        User u1 = new User("testUser1", "123456789", "Patient", account);
        when(view.getUsername()).thenReturn("");
        when(view.getPassword()).thenReturn("123456789");
        when(view.getType()).thenReturn("Doctor");

        verify(view).loginFail();
    }

}

