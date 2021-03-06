package com.example.medicalclinicapp;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainPresenter {
    MainModel mainModel;
    MainActivity mainActivity;
    String key;

    public MainPresenter(MainModel model, MainActivity view){
        this.mainModel = model;
        this.mainActivity = view;
    }

    public ArrayList<String> adjustAvailable(int days){
        ArrayList<String> weekAvailable = new ArrayList<String>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd  HH:mm");
        Date today = new Date();
        Date tomorrow = new Date(today.getYear(),today.getMonth(),(today.getDate()+1));
        // add week available
        for(int i = 0; i < days; i++){
            if(today.getHours()<18){
                for (int j = 0; j<9; j++){
                    weekAvailable.add(format.format(new Date(today.getYear(),today.getMonth(),(today.getDate()+i),9+j,0,0)));
                }
            }
            else{
                for (int j = 0; j<9; j++){
                    weekAvailable.add(format.format(new Date(tomorrow.getYear(),tomorrow.getMonth(),(tomorrow.getDate()+i),9+j,0,0)));
                }
            }
        }
        return weekAvailable;
    }

    public void checkLogin(){
        String username = mainActivity.getUsername();
        String type = mainActivity.getType();
        String password = mainActivity.getPassword();
        mainModel.checkLogin(username,password,type,this.mainModel,this.mainActivity);
    }

    public void login(User u, String k){
        if(u != null){
            key = k;
            mainActivity.loginSuccess(u, k);
        }
        else{
            mainActivity.loginFail();
        }
    }

    public void update(){
        mainModel.updateDoc(this.mainModel,this.mainActivity);
    }

}
