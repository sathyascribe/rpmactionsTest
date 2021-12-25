package com.inventica.rpmapp.ui.activity.connection;

public class MyContactModel {

    String name;
    String phoneNumber;
    boolean isConnection;
    String email;

    public MyContactModel(String name, String phoneNumber, boolean isConnection, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.isConnection = isConnection;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isConnection() {
        return isConnection;
    }

    public void setConnection(boolean connection) {
        isConnection = connection;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
