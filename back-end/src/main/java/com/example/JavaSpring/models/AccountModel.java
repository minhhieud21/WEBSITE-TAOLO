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
    @Field("google_login")
    boolean google_login;
    @Field("urlID")
    String urlID;
    @Field("status")
    int status;

    public AccountModel(){}

    @Override
    public String toString() {
        return "AccountModel{" +
                "_id='" + _id + '\'' +
                ", accID='" + accID + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", google_login=" + google_login +
                ", urlID='" + urlID + '\'' +
                ", status=" + status +
                '}';
    }

    public AccountModel(String _id, String accID, String password, String username, boolean google_login, String urlID, int status) {
        this._id = _id;
        this.accID = accID;
        this.password = password;
        this.username = username;
        this.google_login = google_login;
        this.urlID = urlID;
        this.status = status;
    }


    public boolean isGoogle_login() {
        return google_login;
    }

    public void setGoogle_login(boolean google_login) {
        this.google_login = google_login;
    }

    public String getUrlID() {
        return urlID;
    }

    public void setUrlID(String urlID) {
        this.urlID = urlID;
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
