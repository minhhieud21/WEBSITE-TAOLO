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
    public UserModel getUserByEmail(String email) {
        return userRepository1.getUserByEmail(email);
    }

    @Override
    public void saveUser(UserModel accountModel){
        userRepository1.save(accountModel);
    }

    @Override
    public List<UserModel> getAllUser() {
        return userRepository1.findAll();
    }



    @Override
    public void updateUser(UserModel userModel, String userID) {
        UserModel userModel1 = userRepository1.getUserByUserID(userID);
        userModel.set_id(userModel1.get_id());
        userModel.setUserID(userModel1.getUserID());
        userModel.setGmail(userModel1.getGmail());
        userRepository1.save(userModel);
    }

    @Override
    public List<UserModel> searchUser(String text, int type) {
        if(type == 0){
            return userRepository1.searchUserByGmail(text);
        }
        else {
            return userRepository1.searchUserByPhone(text);
        }
    }
}
