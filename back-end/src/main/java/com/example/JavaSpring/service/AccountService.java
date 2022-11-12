package com.example.JavaSpring.service;

import com.example.JavaSpring.models.AccountModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AccountService {
    Page<AccountModel> getAllAccount(Pageable paging);

    AccountModel getUserByUsername(String username);

    AccountModel getUserByAccID(String accID);

    AccountModel getUserByUrlID(String urlID);

    void saveAccount(AccountModel accountModel);

    List<AccountModel> getAllAccount();

    void updatePassword(AccountModel accountModel);

    void changestatus(AccountModel accountModel);
}
