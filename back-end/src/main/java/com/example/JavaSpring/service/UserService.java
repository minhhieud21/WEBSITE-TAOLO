package com.example.JavaSpring.service;

import com.example.JavaSpring.models.UserModel;

import java.util.List;

public interface UserService {
    UserModel getUserByUserID(String username);

    UserModel getUserByEmail(String email);

    void saveUser(UserModel accountModel);

    List<UserModel> getAllUser();

    void updateUser(UserModel userModel, String userID);

    List<UserModel> searchUser(String text, int type);
}
