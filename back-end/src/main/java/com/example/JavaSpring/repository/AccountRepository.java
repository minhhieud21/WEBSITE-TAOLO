package com.example.JavaSpring.repository;

import com.example.JavaSpring.models.AccountModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends MongoRepository<AccountModel,String> {
    @Query("{username: ?0}") //SELECT * FROM Product WHERE title like ?
    AccountModel getUserByUsername(String username) ;
}
