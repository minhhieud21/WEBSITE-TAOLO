package com.example.JavaSpring.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("User")
public class UserModel {
    @Id
    private String _id;
    @Field("userID")
    String userID;
    @Field("name")
    String name;
    @Field("phone")
    String phone;
    @Field("address")
    String address;
    @Field("gmail")
    String gmail;
    @Field("sex")
    int sex;
    @Field("age")
    int age;

    public UserModel(){}
    public UserModel(String _id, String userID, String name, String phone, String address, String gmail, int sex, int age) {
        this._id = _id;
        this.userID = userID;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.gmail = gmail;
        this.sex = sex;
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "_id='" + _id + '\'' +
                ", userID='" + userID + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", gmail='" + gmail + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                '}';
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
