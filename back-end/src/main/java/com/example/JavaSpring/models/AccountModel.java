package com.example.JavaSpring.models;


import org.springframework.data.mongodb.core.mapping.Document;

@Document("Account")

public class AccountModel {
    String AccID,Username,Password;
    int Status;

    public AccountModel(String accID, String username, String password, int status) {
        AccID = accID;
        Username = username;
        Password = password;
        Status = status;
    }

    @Override
    public String toString() {
        return "AccountModel{" +
                "AccID='" + AccID + '\'' +
                ", Username='" + Username + '\'' +
                ", Password='" + Password + '\'' +
                ", Status=" + Status +
                '}';
    }

    public String getAccID() {
        return AccID;
    }

    public void setAccID(String accID) {
        AccID = accID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }
}
