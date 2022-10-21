package com.example.JavaSpring.service;

import com.example.JavaSpring.models.UserModel;
import com.example.JavaSpring.repository.UserRepository1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository1 userRepository1;

    @Override
    public UserModel getUserByUserID(String username) {
        return userRepository1.getUserByUserID(username);
    }

    @Override
    public void saveUser(UserModel accountModel){
        userRepository1.save(accountModel);
    }

    @Override
    public List<UserModel> getAllUser() {
        return userRepository1.findAll();
    }
}
