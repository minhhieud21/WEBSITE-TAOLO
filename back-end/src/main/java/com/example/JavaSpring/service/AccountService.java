package com.example.JavaSpring.service;

import com.example.JavaSpring.models.AccountModel;

import java.util.List;

public interface AccountService {
    AccountModel getUserByUsername(String username);

    AccountModel getUserByUrlID(String urlID);

    void saveAccount(AccountModel accountModel);

    List<AccountModel> getAllAccount();
}
