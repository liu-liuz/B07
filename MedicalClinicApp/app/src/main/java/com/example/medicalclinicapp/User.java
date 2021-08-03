package com.example.medicalclinicapp;

public class User {
    private String username;
    private String password;
    private String type;
    private UserType account;

    //constructor
    public User(){}
    public  User(String username, String password, String type, UserType account){
        this.password = password;
        this.username = username;
        this.type = type;
        this.account = account;
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

    public UserType getAccount() {
        return account;
    }

    public void setAccount(UserType account) {
        this.account = account;
    }

    @Override
    public String toString(){
        return "Username: "+ this.username + ", "+ "Password: " + this.password + ", " + "Type: " + this.type;
    }
}
