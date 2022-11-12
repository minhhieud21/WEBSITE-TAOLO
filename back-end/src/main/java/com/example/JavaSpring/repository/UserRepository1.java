package com.example.JavaSpring.repository;

import com.example.JavaSpring.models.ProductModel;
import com.example.JavaSpring.models.UserModel;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository1 extends MongoRepository<UserModel,String> {
    @Query("{userID: ?0}") //SELECT * FROM Product WHERE title like ?
    UserModel getUserByUserID(String userID) ;

    @Query("{'gmail': ?0}") //SELECT * FROM Product WHERE title like ?
    UserModel getUserByEmail(String gmail) ;

    @Query("{gmail:/?0/}") //SELECT * FROM Product WHERE title like ?
    List<UserModel> searchUserByGmail(String text) ;

    @Query("{phone:/?0/}") //SELECT * FROM Product WHERE title like ?
    List<UserModel> searchUserByPhone(String text) ;
}
