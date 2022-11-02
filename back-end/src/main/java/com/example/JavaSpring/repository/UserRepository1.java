package com.example.JavaSpring.repository;

import com.example.JavaSpring.models.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository1 extends MongoRepository<UserModel,String> {
    @Query("{userID: ?0}") //SELECT * FROM Product WHERE title like ?
    UserModel getUserByUserID(String userID) ;

    @Query("{'gmail': ?0}") //SELECT * FROM Product WHERE title like ?
    UserModel getUserByEmail(String gmail) ;
}
