package com.example.JavaSpring.service;

import com.example.JavaSpring.models.AccountModel;
import com.example.JavaSpring.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public AccountModel getUserByUsername(String username) {
            return accountRepository.getUserByUsername(username);
    }
}
