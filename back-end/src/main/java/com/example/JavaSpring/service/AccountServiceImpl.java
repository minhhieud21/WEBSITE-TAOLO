package com.example.JavaSpring.service;

import com.example.JavaSpring.models.AccountModel;
import com.example.JavaSpring.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public Page<AccountModel> getAllAccount(Pageable paging) {
        return accountRepository.getAllAccount(paging,false);
    }

    @Override
    public AccountModel getUserByUsername(String username) {
            return accountRepository.getUserByUsername(username);
    }

    @Override
    public AccountModel getUserByAccID(String accID) {
        return accountRepository.getUserByAccID(accID);
    }

    @Override
    public AccountModel getUserByUrlID(String urlID) {
        return accountRepository.getUserByUrlID(urlID);
    }

    @Override
    public void saveAccount(AccountModel accountModel){
        accountRepository.save(accountModel);
    }

    @Override
    public List<AccountModel> getAllAccount() {
        return accountRepository.findAll();
    }

    @Override
    public void updatePassword(AccountModel accountModel){
        accountRepository.save(accountModel);
    }

    @Override
    public void changestatus(AccountModel accountModel){
        accountRepository.save(accountModel);
    }

}
