package com.example.JavaSpring.repository;

import com.example.JavaSpring.models.AccountModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends MongoRepository<AccountModel,String> {
    @Query("{username: ?0}") //SELECT * FROM Product WHERE title like ?
    AccountModel getUserByUsername(String username) ;

    @Query("{urlID: ?0}") //SELECT * FROM Product WHERE title like ?
    AccountModel getUserByUrlID(String urlID) ;
}
