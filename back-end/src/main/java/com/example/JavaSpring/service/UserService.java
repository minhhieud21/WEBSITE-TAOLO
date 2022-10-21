package com.example.JavaSpring.service;

import com.example.JavaSpring.models.UserModel;

import java.util.List;

public interface UserService {
    UserModel getUserByUserID(String username);

    void saveUser(UserModel accountModel);

    List<UserModel> getAllUser();

}
