package com.example.JavaSpring.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("Account")

public class AccountModel {
    @Id
    private String _id;
    @Field("accID")
    String accID;
    @Field("password")
    String password;
    @Field("username")
    String username;
    @Field("status")
    int status;

    @Override
    public String toString() {
        return "AccountModel{" +
                "_id=" + _id +
                ", accID='" + accID + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", status=" + status +
                '}';
    }

    public AccountModel(String _id, String accID, String password, String username, int status) {
        this._id = _id;
        this.accID = accID;
        this.password = password;
        this.username = username;
        this.status = status;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getAccID() {
        return accID;
    }

    public void setAccID(String accID) {
        this.accID = accID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
